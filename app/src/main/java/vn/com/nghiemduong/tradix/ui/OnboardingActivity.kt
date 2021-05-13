package vn.com.nghiemduong.tradix.ui

import android.annotation.SuppressLint
import android.app.ActionBar
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.*
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import vn.com.nghiemduong.tradix.R
import vn.com.nghiemduong.tradix.databinding.ActivityOnboardingBinding
import vn.com.nghiemduong.tradix.ui.onboarding.Onboarding1Fragment
import vn.com.nghiemduong.tradix.ui.onboarding.Onboarding2Fragment
import vn.com.nghiemduong.tradix.ui.onboarding.Onboarding3Fragment
import vn.com.nghiemduong.tradix.utils.*

import kotlin.system.exitProcess
import android.view.View.GONE as GONE

class OnboardingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnboardingBinding

    private var mPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)

        if (getShipTutorialPref(applicationContext)) {
            startActivity(Intent(this, LoginActivity::class.java))
        } else if (getCheckLoginPref(applicationContext)) {
            startActivity(Intent(this, MainActivity::class.java))
        }

        setContentView(binding.root)

        addDots()

        replaceAddToBackStackFragmentWithAnimationOnboading(
            supportFragmentManager, Onboarding1Fragment(),
            binding.frameOnboarding.id, "Onboarding1Fragment",
            applicationContext, binding.frameOnboarding
        )

        binding.ivBackArrow.visibility = GONE

        binding.tvActionNextStart.setOnClickListener {
            when (mPosition) {
                0 -> {
                    mPosition = 1
                    replaceAddToBackStackFragmentWithAnimationOnboading(
                        supportFragmentManager, Onboarding2Fragment(),
                        binding.frameOnboarding.id, "Onboarding2Fragment",
                        applicationContext, binding.frameOnboarding
                    )
                    replaceDots()
                    binding.ivBackArrow.visibility = VISIBLE
                }
                1 -> {
                    mPosition = 2
                    replaceAddToBackStackFragmentWithAnimationOnboading(
                        supportFragmentManager, Onboarding3Fragment(),
                        binding.frameOnboarding.id, "Onboarding3Fragment",
                        applicationContext, binding.frameOnboarding
                    )
                    binding.tvActionNextStart.text = "START"
                    replaceDots()
                    binding.ivBackArrow.visibility = VISIBLE
                }
                else -> {
                    updateShipTutorialPref(applicationContext, true)
                    startActivity(Intent(this, LoginActivity::class.java))
                }
            }
        }

        binding.tvSkip.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.ivBackArrow.setOnClickListener {
            onBackPressed()
        }
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

    override fun onBackPressed() {
        val indexFragment = supportFragmentManager.backStackEntryCount
        if (indexFragment == 1) {
            exitProcess(0)
        } else {
            if (indexFragment == 2) {
                binding.ivBackArrow.visibility = GONE
            }
            when (supportFragmentManager.getBackStackEntryAt(indexFragment - 2).name) {
                "Onboarding1Fragment" -> {
                    binding.tvActionNextStart.text = "NEXT"
                    mPosition = 0
                    replaceDots()
                }
                "Onboarding2Fragment" -> {
                    binding.tvActionNextStart.text = "NEXT"
                    mPosition = 1
                    replaceDots()
                }
                else -> {
                    binding.tvActionNextStart.text = "START"
                    mPosition = 2
                    replaceDots()
                }
            }
            val animation = AnimationUtils.loadAnimation(
                applicationContext,
                R.anim.move_fragment_back_onboarding
            )
            binding.frameOnboarding.startAnimation(animation)
            super.onBackPressed()
        }
    }
}