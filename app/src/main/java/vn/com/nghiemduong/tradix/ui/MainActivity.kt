package vn.com.nghiemduong.tradix.ui

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.DialogInterface
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.PorterDuff
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import vn.com.nghiemduong.tradix.R
import vn.com.nghiemduong.tradix.R.color.white
import vn.com.nghiemduong.tradix.databinding.ActivityMainBinding
import vn.com.nghiemduong.tradix.ui.main.*
import vn.com.nghiemduong.tradix.utils.replaceAddToBackStackFragment
import vn.com.nghiemduong.tradix.viewmodel.NewsViewModel
import kotlin.system.exitProcess

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

    lateinit var newsViewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        newsViewModel = ViewModelProvider(this).get(NewsViewModel::class.java)

        if (newsViewModel.news == null) {
            replaceFragmentMain(HomeFragment(), "HomeFragment")
        }

        binding.ctlHome.setOnClickListener {
            val index = supportFragmentManager.backStackEntryCount
            if (supportFragmentManager.getBackStackEntryAt(index - 1).name != "HomeFragment") {
                setBackgroundAllNavNull()
                setBackgroundNavSelected(binding.ctlHome, binding.ivHome)
                replaceFragmentMain(HomeFragment(), "HomeFragment")
            }
        }

        binding.ctlCoin.setOnClickListener {
            val index = supportFragmentManager.backStackEntryCount
            if (supportFragmentManager.getBackStackEntryAt(index - 1).name != "CoinFragment") {
                setBackgroundAllNavNull()
                setBackgroundNavSelected(binding.ctlCoin, binding.ivCoin)
                replaceFragmentMain(CoinFragment(), "CoinFragment")
            }
        }

        binding.ctlNews.setOnClickListener {
            val index = supportFragmentManager.backStackEntryCount
            when (supportFragmentManager.getBackStackEntryAt(index - 1).name) {
                "NewsFragment" -> {

                }

                "NewsArticleFragment" -> {
                    onBackPressed()
                }

                else -> {
                    setBackgroundAllNavNull()
                    setBackgroundNavSelected(binding.ctlNews, binding.ivNews)
                    replaceFragmentMain(NewsFragment(), "NewsFragment")
                }
            }
        }

        binding.ctlMenu.setOnClickListener {
            val index = supportFragmentManager.backStackEntryCount
            if (supportFragmentManager.getBackStackEntryAt(index - 1).name != "MenuFragment") {
                setBackgroundAllNavNull()
                setBackgroundNavSelected(binding.ctlMenu, binding.ivMenu)
                replaceFragmentMain(MenuFragment(), "MenuFragment")
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
            dialog.setNegativeButton("Yes", DialogInterface.OnClickListener { dialogInterface, i ->
                this.finish()
            })

            dialog.setPositiveButton("No") { _, _ -> }
            dialog.show()
        } else {
            val nameFragment = supportFragmentManager.getBackStackEntryAt(index - 2).name
            setBackgroundAllNavNull()
            when (nameFragment) {
                "HomeFragment" -> {
                    setBackgroundNavSelected(binding.ctlHome, binding.ivHome)
                }

                "CoinFragment" -> {
                    setBackgroundNavSelected(binding.ctlCoin, binding.ivCoin)
                }

                "NewsFragment" -> {
                    setBackgroundNavSelected(binding.ctlNews, binding.ivNews)
                }

                "MenuFragment" -> {
                    setBackgroundNavSelected(binding.ctlMenu, binding.ivMenu)
                }

                else -> {
                }
            }
            super.onBackPressed()
        }
    }

    fun replaceFragmentMainSetArguments(fragment: Fragment, bundle: Bundle, nameFragment: String) {
        fragment.arguments = bundle
        replaceFragmentMain(fragment, nameFragment)
    }

    fun replaceFragmentMain(fragment: Fragment, nameFragment: String) {
        replaceAddToBackStackFragment(
            supportFragmentManager,
            fragment,
            binding.frameMain.id,
            nameFragment
        )
    }
}