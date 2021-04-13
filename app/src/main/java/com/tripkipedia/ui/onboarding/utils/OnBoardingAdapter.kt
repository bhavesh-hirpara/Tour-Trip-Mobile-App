package com.tripkipedia.ui.onboarding.utils

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager.widget.PagerAdapter
import com.tripkipedia.R
import com.tripkipedia.apputils.Utils
import com.tripkipedia.ui.onboarding.datamodel.MenuItem
/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
@Suppress("UNREACHABLE_CODE")
class OnBoardingAdapter(private val mContext: Context) : PagerAdapter() {

    private val COUNT = 3
    private val data = mutableListOf<MenuItem>()

    private val inflater: LayoutInflater = LayoutInflater.from(mContext)

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }


    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun getCount(): Int {
        return 3
    }

    @SuppressLint("ResourceType")
    override fun instantiateItem(view: ViewGroup, position: Int): Any {
        val imageLayout = inflater.inflate(R.layout.adapter_onboarding, view, false)

        val imageView = imageLayout!!
            .findViewById(R.id.imgMain) as ImageView

        val title = imageLayout
            .findViewById(R.id.tvTitle) as TextView
        title.text = data[position].title

        val textView = imageLayout.findViewById(R.id.tvCollect) as TextView
        textView.text = data[position].title_collect

        imageView.setImageResource(data[position].image)

        val mainBack = imageLayout.findViewById(R.id.lyMainBack) as LinearLayout
        mainBack.setBackgroundColor(Color.parseColor(data[position].color))

        /*Utils.loadImage(
            imageView,
            data[position].image.toString(),
            mContext,
            R.drawable.imageplaceholder
        )*/

        view.addView(imageLayout, 0)
        return imageLayout

    }

    fun addAll(mData: ArrayList<MenuItem>) {
        data.clear()
        data.addAll(mData)
        notifyDataSetChanged()
    }

    fun getItem(position: Int): MenuItem {
        return data[position]
    }


    /*override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = OnBoardingFragment()
            1 -> fragment = OnBoardingFragment()
            2 -> fragment = OnBoardingFragment()

        }
        return fragment!!
    }

    override fun getCount(): Int {
        return COUNT
    }

    override fun getItemPosition(`object`: Any): Int {
        return PagerAdapter.POSITION_NONE;
    }*/
}
