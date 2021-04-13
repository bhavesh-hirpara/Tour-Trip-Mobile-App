package com.tripkipedia.ui.dashboard.home.utils

import android.content.Context
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.viewpager.widget.PagerAdapter
import com.google.android.gms.maps.model.LatLng
import com.tripkipedia.R
import com.tripkipedia.apputils.Utils
import com.tripkipedia.custom.CBTextView
import com.tripkipedia.custom.CTextView
import com.tripkipedia.ui.dashboard.datamodel.ItemsData
import java.util.*

/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class SlidingImageAdapter(private val context: Context) : PagerAdapter() {

    private lateinit var dataView: View
    private val data = ArrayList<ItemsData.Datum>()
    private val inflater: LayoutInflater
    private lateinit var mEventListener: EventListener

    init {
        inflater = LayoutInflater.from(context)
    }

    interface EventListener {
        fun onItemViewClicked(position: Int, itemId: String?)
    }


    fun addAll(mData: MutableList<ItemsData.Datum>) {
        data.clear()
        data.addAll(mData)
        notifyDataSetChanged()
    }

    fun clear() {
        data.clear()
        notifyDataSetChanged()
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun getCount(): Int {
        return data.size
    }

    fun setEventListener(eventListener: EventListener) {
        mEventListener = eventListener
    }

    fun getLatLong(position: Int): LatLng {
        return LatLng(
            data.get(position).latitude!!.toDouble(),
            data.get(position).longitude!!.toDouble()
        )
    }

    override fun instantiateItem(view: ViewGroup, position: Int): Any {
        dataView = inflater.inflate(R.layout.item_home, view, false)
        dataView.setOnClickListener {
            mEventListener.onItemViewClicked(position, data.get(position).itemId)
        }
        val imageView = dataView
            .findViewById<View>(R.id.imgIcon) as AppCompatImageView
        val txtTitle = dataView
            .findViewById<View>(R.id.txtTitle) as CBTextView
        val txtType = dataView
            .findViewById<View>(R.id.txtType) as CTextView
        val txtReview = dataView
            .findViewById<View>(R.id.txtReview) as CTextView

        Utils.loadImage(imageView, data.get(position).photo!!, context, R.mipmap.item_card_image)
        txtTitle.text = data.get(position).name
        txtType.text = data.get(position).type
        txtReview.text = "" + data.get(position).reviews + " Reviews"


        /*val item = data[position]

        assert(imageLayout != null)
        val imageView = imageLayout!!
                .findViewById<View>(R.id.image) as ImageView
               final TextView tvTitle = (TextView) imageLayout
        //                .findViewById(R.id.tvTitle);
        //        final TextView tvDesc = (TextView) imageLayout
        //                .findViewById(R.id.tvDesc);

        imageView.setImageResource(R.mipmap.placeholder)
        if (item.img.isNullOrEmpty().not()) {
            Utils.loadImage(imageView,item.img!!,context,R.mipmap.placeholder)
        } else {
            imageView.setImageResource(R.mipmap.placeholder)
        }

        imageView.setOnClickListener {
            if (mEventListener != null) {
                mEventListener!!.onItemViewClicked(position)
            }
        }*/

        view.addView(dataView, 0)

        return dataView
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun restoreState(state: Parcelable?, loader: ClassLoader?) {}

    override fun saveState(): Parcelable? {
        return null
    }

}