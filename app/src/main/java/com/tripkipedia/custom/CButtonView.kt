package com.tripkipedia.custom

import android.content.Context
import androidx.appcompat.widget.AppCompatButton
import android.util.AttributeSet

import com.tripkipedia.apputils.Utils


/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class CButtonView : AppCompatButton {

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context) : super(context) {
        init()
    }

    fun init() {
        if (!isInEditMode) {
            try {
                typeface = Utils.getRegular(context)
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

}