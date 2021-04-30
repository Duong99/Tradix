package vn.com.nghiemduong.tradix.utils

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

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