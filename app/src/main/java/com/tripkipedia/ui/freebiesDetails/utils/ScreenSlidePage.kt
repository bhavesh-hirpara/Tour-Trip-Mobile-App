package com.tripkipedia.ui.freebiesDetails.utils


import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.tripkipedia.ui.freebiesDetails.details.fragment.DetailsFragment
import com.tripkipedia.ui.freebiesDetails.freebies.fragment.FreebiesFragment

/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class ScreenSlidePage : FragmentPagerAdapter {
    var itemId:String?=null
    private val tabTitles =
        arrayOf("Freebies", "Details")


    constructor(fm: FragmentManager, itemId: String?) : super(fm!!) {
        this.itemId=itemId
    }


    override fun getItem(position: Int): Fragment {
        var f: Fragment? = Fragment()
        when (position) {
            0 -> f = FreebiesFragment()
            1 -> f = DetailsFragment()


        }
        val args = Bundle()
        args.putString("itemId",itemId)
        f!!.setArguments(args)
        return f
    }


    override fun getCount(): Int {
        return tabTitles.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tabTitles[position]
    }

}