package tv.brid.videos.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load

@BindingAdapter("android:setImageAsync")
fun ImageView.setImageAsync(uri: String) {
    this.load(uri)
}