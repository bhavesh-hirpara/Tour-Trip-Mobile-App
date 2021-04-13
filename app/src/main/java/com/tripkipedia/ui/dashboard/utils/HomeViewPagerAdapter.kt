package com.tripkipedia.ui.dashboard.utils


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class HomeViewPagerAdapter : FragmentPagerAdapter {

    private val mFragmentList: ArrayList<Fragment> = ArrayList()

    constructor(fm: FragmentManager) : super(fm) {
    }

    override fun getItem(position: Int): Fragment {
        return mFragmentList.get(position)
    }


    override fun getCount(): Int {
        return mFragmentList.size
    }

    fun addFragment(fragment: Fragment?) {
        mFragmentList.add(fragment!!)
    }


}