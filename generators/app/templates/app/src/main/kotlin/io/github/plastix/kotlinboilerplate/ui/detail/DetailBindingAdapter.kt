package <%= appPackage %>.ui.detail

import android.databinding.BindingAdapter
import android.widget.ImageView
import <%= appPackage %>.extensions.loadImage

@BindingAdapter("android:src")
fun setImageBinding(view: ImageView, url: String){
    view.loadImage(url)
}