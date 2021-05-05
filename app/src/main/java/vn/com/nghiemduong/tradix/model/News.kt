package vn.com.nghiemduong.tradix.model

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import java.io.Serializable

class News(
    var image: Int,
    var discount: String,
    var name: String,
    var content: String,
    var date: String
) : Serializable {
}

@BindingAdapter("android:imageUrl")
fun loadImage(view: ImageView, image: Int) {
    view.setBackgroundResource(image)
}