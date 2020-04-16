package yovi.putra.github.core.utils.ui

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_of_user_list.view.*
import yovi.putra.github.R

fun ImageView.load(
    context: Context,
    path: String?,
    placeholder: Int = R.mipmap.ic_launcher,
    rounded: Boolean = false
) {
    Glide.with(context)
        .load(path)
        .placeholder(placeholder)
        .apply(
            if (rounded) RequestOptions.circleCropTransform()
            else RequestOptions.noTransformation()
        )
        .into(this)
}
