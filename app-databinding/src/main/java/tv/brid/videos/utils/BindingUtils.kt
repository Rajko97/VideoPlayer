package tv.brid.videos.utils

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.size.Scale
import tv.brid.videos.R

@BindingAdapter("android:setImageAsync")
fun ImageView.setImageAsync(uri: String) {
    this.load(uri) {
        placeholder(R.drawable.ic_launcher_foreground)
        scale(Scale.FILL)
    }
}

@BindingAdapter("android:visibility")
fun View.setVisibility(visible: Boolean) {
    this.visibility = if (visible) View.VISIBLE else View.GONE
}