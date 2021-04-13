package com.tripkipedia.apputils

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter

/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
object CommonBindingUtils {


    fun setImageResource(imageView: AppCompatImageView, resId: Int) {
        imageView.setImageResource(resId)
    }


}
