package vn.com.nghiemduong.tradix.model

import android.widget.ImageView
import androidx.databinding.BindingAdapter

class News(
    var image: Int,
    var discount: String,
    var name: String,
    var content: String,
    var date: String
) {
}

@BindingAdapter("android:imageUrl")
fun loadImage(view: ImageView, image: Int) {
    view.setBackgroundResource(image)
}