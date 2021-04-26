package vn.com.nghiemduong.tradix.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter

class IntroViewPagerAdapter(var layoutIds: Array<Int>) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layoutInflater: LayoutInflater = LayoutInflater.from(container.context)
        val view = layoutInflater.inflate(layoutIds[position], container, false)
        container.addView(view)
        return view
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean = view == `object`

    override fun getCount(): Int = layoutIds.size

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val view = `object` as View
        container.removeView(view)
    }
}