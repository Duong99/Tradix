package vn.com.nghiemduong.tradix.utils

import android.content.Context
import android.view.View
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import vn.com.nghiemduong.tradix.R

fun replaceAddToBackStackFragment(
    fragmentManager: FragmentManager,
    fragment: Fragment,
    layoutFrameId: Int,
    name: String
) {
    fragmentManager.beginTransaction()
        .replace(layoutFrameId, fragment)
        .addToBackStack(name).commit()
}

fun replaceAddToBackStackFragmentWithAnimationOnboading(
    fragmentManager: FragmentManager,
    fragment: Fragment,
    layoutFrameId: Int,
    name: String,
    context: Context,
    view: View
) {
    val animation = AnimationUtils.loadAnimation(context, R.anim.move_fragment_start_onboarding)
    view.startAnimation(animation)

    fragmentManager.beginTransaction()
        .replace(layoutFrameId, fragment)
        .addToBackStack(name).commit()
}