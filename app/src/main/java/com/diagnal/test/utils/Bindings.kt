package com.diagnal.test.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.diagnal.test.R

class Bindings {
    companion object{

        val options = RequestOptions()
            .placeholder(R.drawable.placeholder_for_missing_posters)
            .error(R.drawable.placeholder_for_missing_posters)
        @JvmStatic
        @BindingAdapter("imageUrl")
        fun loadImage(view: ImageView, url: String) {
            val resources = view.context.resources
            val name = url.split(".")[0]
            val drawableResourceId: Int = resources.getIdentifier(name, "drawable", view.context.packageName)
            Glide.with(view.context).load(drawableResourceId).apply(options).into(view)
        }
    }
}