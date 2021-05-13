package vn.com.nghiemduong.tradix.ui

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import vn.com.nghiemduong.tradix.R
import vn.com.nghiemduong.tradix.databinding.ActivityMainBinding
import vn.com.nghiemduong.tradix.ui.main.CoinFragment
import vn.com.nghiemduong.tradix.ui.main.HomeFragment
import vn.com.nghiemduong.tradix.ui.main.MenuFragment
import vn.com.nghiemduong.tradix.ui.main.NewsFragment
import vn.com.nghiemduong.tradix.utils.*


/*
 yêu cầu bài tập:
- khi chưa hoàn thành các màn tutorial/ skip thì khi mở app phải show màn tutorial
- sau khi đăng ký thì chuyển đến màn login và tự động điền thông tin đã đăng ký(chú ý sự hợp lý khi ấn back)
- màn hình email số 30 sẽ đếm ngược
- màn hình main: khi ấn back sẽ back lần lượt các tab đã click, khi không còn tab nào thì có dialog xác nhận thoát app.
- tab Home: có chức năng loadmore, mỗi lần load thêm 10 item, name kèm theo index; có chức năng xóa (itemtouchhelper)
- các button hình chuông thông báo thay đổi background mỗi khi click
- tab News: khi sang detail thì hiện title của news
- tab Menu: Chỉ dùng 1 Recycler(dùng ViewType)
- UI thiết kế y như design(những phần nào khác thì confirm với a)
- hạn nộp bài 14/5
 */

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replaceFragmentMain(HomeFragment(), NAME_HOME_FRAGMENT, TAG_HOME_FRAGMENT)

        binding.ctlHome.setOnClickListener {
            val index = supportFragmentManager.backStackEntryCount
            if (supportFragmentManager.getBackStackEntryAt(index - 1).name != NAME_HOME_FRAGMENT) {
                setBackgroundAllNavNull()
                setBackgroundNavSelected(binding.ctlHome, binding.ivHome)
                replaceFragmentMain(HomeFragment(), NAME_HOME_FRAGMENT, TAG_HOME_FRAGMENT)
            }
        }

        binding.ctlCoin.setOnClickListener {
            val index = supportFragmentManager.backStackEntryCount
            if (supportFragmentManager.getBackStackEntryAt(index - 1).name != NAME_COIN_FRAGMENT) {
                removePopBackStackFragment(NAME_COIN_FRAGMENT)
                setBackgroundAllNavNull()
                setBackgroundNavSelected(binding.ctlCoin, binding.ivCoin)
                replaceFragmentMain(CoinFragment(), NAME_COIN_FRAGMENT, TAG_COIN_FRAGMENT)
            }
        }

        binding.ctlNews.setOnClickListener {
            val index = supportFragmentManager.backStackEntryCount
            when (supportFragmentManager.getBackStackEntryAt(index - 1).name) {
                NAME_NEWS_FRAGMENT -> {

                }

                NAME_NEWS_ARTICLE_FRAGMENT -> {
                    onBackPressed()
                }

                else -> {
                    removePopBackStackFragment(NAME_NEWS_FRAGMENT)
                    setBackgroundAllNavNull()
                    setBackgroundNavSelected(binding.ctlNews, binding.ivNews)
                    replaceFragmentMain(NewsFragment(), NAME_NEWS_FRAGMENT, TAG_NEWS_FRAGMENT)
                }
            }
        }

        binding.ctlMenu.setOnClickListener {
            val index = supportFragmentManager.backStackEntryCount
            if (supportFragmentManager.getBackStackEntryAt(index - 1).name != NAME_MENU_FRAGMENT) {
                removePopBackStackFragment(NAME_MENU_FRAGMENT)
                setBackgroundAllNavNull()
                setBackgroundNavSelected(binding.ctlMenu, binding.ivMenu)
                replaceFragmentMain(MenuFragment(), NAME_MENU_FRAGMENT, TAG_MENU_FRAGMENT)
            }
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables", "ResourceType")
    private fun setBackgroundNavSelected(constraintLayout: ConstraintLayout, iv: ImageView) {
        constraintLayout.background = applicationContext.resources.getDrawable(
            R.drawable.bg_nav_item_main,
            applicationContext.theme
        )

        iv.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#ffffff"))
    }

    @SuppressLint("UseCompatLoadingForDrawables", "ResourceType")
    private fun setBackgroundNavUnselect(constraintLayout: ConstraintLayout, iv: ImageView) {
        constraintLayout.background = applicationContext.resources.getDrawable(
            R.drawable.bg_item_nav_unselect,
            applicationContext.theme
        )
        iv.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#3A3A3A"))
    }

    private fun setBackgroundAllNavNull() {
        setBackgroundNavUnselect(binding.ctlHome, binding.ivHome)
        setBackgroundNavUnselect(binding.ctlCoin, binding.ivCoin)
        setBackgroundNavUnselect(binding.ctlNews, binding.ivNews)
        setBackgroundNavUnselect(binding.ctlMenu, binding.ivMenu)
    }

    override fun onBackPressed() {
        val index = supportFragmentManager.backStackEntryCount
        if (index == 1) {
            val dialog = AlertDialog.Builder(this)
            dialog.setTitle("Close app")
            dialog.setMessage("You are close app???")
            dialog.setNegativeButton("Yes", DialogInterface.OnClickListener { _, _ ->

                val homeIntent = Intent(Intent.ACTION_MAIN)
                homeIntent.addCategory(Intent.CATEGORY_HOME)
                homeIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(homeIntent)
            })

            dialog.setPositiveButton("No") { _, _ -> }
            dialog.show()

        } else {
            val nameFragment = supportFragmentManager.getBackStackEntryAt(index - 2).name
            setBackgroundAllNavNull()
            when (nameFragment) {
                NAME_HOME_FRAGMENT -> {
                    setBackgroundNavSelected(binding.ctlHome, binding.ivHome)
                }

                NAME_COIN_FRAGMENT -> {
                    setBackgroundNavSelected(binding.ctlCoin, binding.ivCoin)
                }

                NAME_NEWS_FRAGMENT -> {
                    setBackgroundNavSelected(binding.ctlNews, binding.ivNews)
                }

                NAME_MENU_FRAGMENT -> {
                    setBackgroundNavSelected(binding.ctlMenu, binding.ivMenu)
                }

                NAME_NEWS_ARTICLE_FRAGMENT -> {
                    setBackgroundNavSelected(binding.ctlNews, binding.ivNews)
                }

                else -> {
                }
            }
            super.onBackPressed()
        }
    }

    fun replaceFragmentMainSetArguments(
        fragment: Fragment,
        bundle: Bundle,
        nameFragment: String,
        tagFragment: String
    ) {
        fragment.arguments = bundle
        removePopBackStackFragment(nameFragment)
        replaceFragmentMain(fragment, nameFragment, tagFragment)
    }

    private fun replaceFragmentMain(fragment: Fragment, nameFragment: String, tagFragment: String) {
        replaceAddToBackStackFragment(
            supportFragmentManager,
            fragment,
            binding.frameMain.id,
            nameFragment,
            tagFragment
        )
    }

    private fun removePopBackStackFragment(nameFragment: String) {
      /*  val listNameFragment: MutableList<String> = mutableListOf()
        val count = supportFragmentManager.backStackEntryCount
        if (count > 1) {
            for (i in (count - 1) downTo 1) {
                if (nameFragment == supportFragmentManager.getBackStackEntryAt(i).name) {
                    supportFragmentManager.popBackStack(
                        supportFragmentManager.getBackStackEntryAt(i).name,
                        FragmentManager.POP_BACK_STACK_INCLUSIVE
                    )

                    break
                } else {
                    listNameFragment.add(supportFragmentManager.getBackStackEntryAt(i).name ?: "")
                }
            }

            if (listNameFragment.size != 0 && listNameFragment.size != count - 1) {
                for (j in listNameFragment.size - 1 downTo 0) {
                    supportFragmentManager.beginTransaction().addToBackStack(listNameFragment[j])
                }
            }
        }*/
    }

    private fun exitApp() {
        System.exit(0)
    }
}