package com.tripkipedia.ui.myTickets.current.viewmodel

import android.app.Application
import android.content.Context
import com.tripkipedia.base.viewmodel.BaseViewModel
import com.tripkipedia.databinding.FragmentCurrentBinding
import com.tripkipedia.ui.myTickets.current.utils.CurrentAdapter

/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class CurrentViewModel(application: Application) : BaseViewModel(application) {

    private lateinit var binder: FragmentCurrentBinding
    lateinit var mContext: Context
    private var dataCurrent: MutableList<String> = ArrayList<String>()
    lateinit var currentAdapter: CurrentAdapter


    fun setBinder(binder: FragmentCurrentBinding) {
        this.binder = binder
        this.mContext = binder.root.context
        binder.viewModel = this
        binder.viewClickHandler = ViewClickHandler()
        init()

    }

    fun init() {

        for (i: Int in 0..9) {
            dataCurrent.add("l" + i)
        }

        currentAdapter = CurrentAdapter(mContext)


        currentAdapter.addAll(dataCurrent as ArrayList<String>)

        binder.rvCurrent.adapter = currentAdapter

    }

    inner class ViewClickHandler {
        fun onExtendClick() {


        }

    }


}


