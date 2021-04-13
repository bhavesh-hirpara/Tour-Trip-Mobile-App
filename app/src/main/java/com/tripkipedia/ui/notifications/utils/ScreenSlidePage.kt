package com.tripkipedia.ui.notifications.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.tripkipedia.ui.notifications.invites.fragment.InvitesFragment
import com.tripkipedia.ui.notifications.updates.fragment.UpdatesFragment

/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class ScreenSlidePage : FragmentPagerAdapter {

    private val tabTitles =
        arrayOf("Updates", "Invites")


    constructor(fm: FragmentManager) : super(fm!!) {
    }


    override fun getItem(position: Int): Fragment {
        var f: Fragment? = Fragment()
        when (position) {
            0 -> f = UpdatesFragment()
            1 -> f = InvitesFragment()


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