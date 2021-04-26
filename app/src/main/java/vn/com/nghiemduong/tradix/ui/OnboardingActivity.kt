package vn.com.nghiemduong.tradix.ui

import android.annotation.SuppressLint
import android.app.ActionBar
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.core.view.marginStart
import androidx.viewpager.widget.ViewPager
import vn.com.nghiemduong.tradix.R
import vn.com.nghiemduong.tradix.adapter.IntroViewPagerAdapter
import vn.com.nghiemduong.tradix.databinding.ActivityOnboardingBinding

class OnboardingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnboardingBinding

    private lateinit var mLayoutIdIntros: Array<Int>
    private lateinit var mIntroAdapter: IntroViewPagerAdapter
    private var mPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)

        setContentView(binding.root)

        addListLayoutIntro()

        addDots()

        setUpViewPagerWithLayoutIntro()

        binding.tvActionNextStart.setOnClickListener {
            if (mPosition == 0) {
                mPosition++
                binding.vpIntro.currentItem = mPosition
            } else if (mPosition == 1) {
                mPosition++
                binding.vpIntro.currentItem = mPosition
                binding.tvActionNextStart.text = "START"
            } else {
                startActivity(Intent(this, LoginActivity::class.java))
            }
        }

        binding.vpIntro.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                mPosition = position
                if (mPosition == 2) {
                    binding.tvActionNextStart.text = "START"
                } else {
                    binding.tvActionNextStart.text = "NEXT"
                }
                replaceDots()
            }

        })
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun addDots() {
        for (i in 0..2) {
            val ivDots = ImageView(applicationContext)
            val layoutParams = ActionBar.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.WRAP_CONTENT
            )

            layoutParams.marginStart = 15
            ivDots.layoutParams = layoutParams

            if (i == mPosition) {
                ivDots.background = resources.getDrawable(R.drawable.bg_dot_selected)

            } else {
                ivDots.background = resources.getDrawable(R.drawable.bg_dot_unselect)
            }

            binding.llDots.addView(ivDots, i)
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun replaceDots() {
        for (i in 0..2) {
            if (i == mPosition) {
                binding.llDots.getChildAt(mPosition).background =
                    resources.getDrawable(R.drawable.bg_dot_selected)
            } else {
                binding.llDots.getChildAt(i).background =
                    resources.getDrawable(R.drawable.bg_dot_unselect)
            }
        }

    }

    private fun setUpViewPagerWithLayoutIntro() {
        mIntroAdapter = IntroViewPagerAdapter(mLayoutIdIntros)
        binding.vpIntro.adapter = mIntroAdapter
    }

    private fun addListLayoutIntro() {
        mLayoutIdIntros =
            arrayOf(R.layout.layout_intro_1, R.layout.layout_intro_2, R.layout.layout_intro_3)

    }
}