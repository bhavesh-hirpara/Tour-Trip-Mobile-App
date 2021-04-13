package com.tripkipedia.ui.myTickets.past.viewmodel

import android.app.Application
import android.content.Context
import com.tripkipedia.base.viewmodel.BaseViewModel
import com.tripkipedia.databinding.FragmentCurrentBinding
import com.tripkipedia.databinding.FragmentInvitesBinding
import com.tripkipedia.databinding.FragmentPastBinding
import com.tripkipedia.ui.myTickets.current.utils.CurrentAdapter
import com.tripkipedia.ui.myTickets.past.utils.PastAdapter

/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class PastViewModel(application: Application) : BaseViewModel(application) {

    private lateinit var binder: FragmentPastBinding
    lateinit var mContext: Context
    private var dataPast: MutableList<String> = ArrayList<String>()
    lateinit var pastAdapter: PastAdapter


    fun setBinder(binder: FragmentPastBinding) {
        this.binder = binder
        this.mContext = binder.root.context
        binder.viewModel = this
        binder.viewClickHandler = ViewClickHandler()
        init()

    }

    fun init() {

        for (i: Int in 0..9) {
            dataPast.add("l" + i)
        }

        pastAdapter = PastAdapter(mContext)


        pastAdapter.addAll(dataPast as ArrayList<String>)

        binder.rvPast.adapter = pastAdapter

    }

    inner class ViewClickHandler {
        fun onExtendClick() {


        }

    }


}


