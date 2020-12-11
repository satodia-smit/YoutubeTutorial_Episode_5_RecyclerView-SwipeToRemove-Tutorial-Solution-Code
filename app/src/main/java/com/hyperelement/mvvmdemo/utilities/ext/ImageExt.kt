package com.hyperelement.mvvmdemo.utilities.ext

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.hyperelement.mvvmdemo.R
import timber.log.Timber
import java.io.File

@BindingAdapter(value = ["bind:image", "placeholderImage", "errorImage"], requireAll = false)

fun loadImageWithGlide(
    imageView: AppCompatImageView,
    obj: Any?,
    placeholder: Any?,
    errorImage: Any?
) {

    val options = RequestOptions()
    if (placeholder is Drawable) options.placeholder(placeholder as Drawable?)
    if (placeholder is Int) options.placeholder((placeholder as Int?)!!)
    if (errorImage is Drawable) options.error(errorImage as Drawable?)
    if (errorImage is Int) options.error((errorImage as Int?)!!)

    val manager: RequestManager = Glide.with(imageView).applyDefaultRequestOptions(options)

    var builder: RequestBuilder<Drawable>
    when (obj) {
        is String -> {
            builder = manager.load(obj as String?)
        }
        is Uri -> builder = manager.load(obj as Uri?)
        is Drawable -> builder =
            manager.load(obj as Drawable?)
        is Bitmap -> builder =
            manager.load(obj as Bitmap?)
        is Int -> builder =
            manager.load(obj as Int?)
        is File -> builder =
            manager.load(obj as File?)
        is Array<*> -> builder =
            manager.load(obj as Array<*>?)
        else -> builder = manager.load(obj)
    }
    builder = builder.transition(DrawableTransitionOptions.withCrossFade())

    builder.into(imageView)
}

@BindingAdapter(
    value = ["imageURL", "placeHolder", "errorHolder", "isCircle", "isRounded", "radius"],
    requireAll = false
)
fun loadImage(
    view: AppCompatImageView, imageURL: String? = "",
    placeHolder: Int = R.mipmap.ic_launcher,
    errorHolder: Int = R.mipmap.ic_launcher,
    isCircle: Boolean = false,
    isRounded: Boolean = false,
    radius: Float = 0f
) {
    Timber.e("URL : ${imageURL}")
}
