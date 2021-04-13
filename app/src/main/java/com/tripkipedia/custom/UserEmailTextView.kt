package com.tripkipedia.custom

import android.content.Context
import androidx.appcompat.widget.AppCompatTextView
import android.util.AttributeSet
import com.tripkipedia.R


/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class UserEmailTextView : AppCompatTextView {

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}


    override fun setSelected(selected: Boolean) {
        super.setSelected(selected)
        setCompoundDrawablesWithIntrinsicBounds(0, 0, if (selected) R.drawable.ic_cancel_red_18dp else 0, 0)
    }
}
