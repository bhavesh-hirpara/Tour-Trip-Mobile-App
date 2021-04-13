package com.tripkipedia.custom

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import com.tripkipedia.apputils.Utils


/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class CBEditTextView : AppCompatEditText {

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
                typeface = Utils.getBold(context)
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

}