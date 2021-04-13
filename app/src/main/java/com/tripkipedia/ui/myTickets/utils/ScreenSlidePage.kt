package com.tripkipedia.ui.myTickets.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.tripkipedia.ui.myTickets.current.fragment.CurrentFragment
import com.tripkipedia.ui.myTickets.past.fragment.PastFragment

/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class ScreenSlidePage : FragmentPagerAdapter {

    private val tabTitles =
        arrayOf("Current", "Past")


    constructor(fm: FragmentManager) : super(fm!!) {
    }


    override fun getItem(position: Int): Fragment {
        var f: Fragment? = Fragment()
        when (position) {
            0 -> f = CurrentFragment()
            1 -> f = PastFragment()


        }
        return f!!
    }


    override fun getCount(): Int {
        return tabTitles.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tabTitles[position]
    }

}