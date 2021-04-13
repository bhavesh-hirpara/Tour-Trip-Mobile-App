package com.tripkipedia.ui.notifications.updates.viewmodel

import android.app.Application
import android.content.Context
import com.tripkipedia.base.viewmodel.BaseViewModel
import com.tripkipedia.databinding.FragmentUpdatesBinding
import com.tripkipedia.ui.notifications.updates.utils.UpdatesAdapter

/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class UpdatesViewModel(application: Application) : BaseViewModel(application) {

    private lateinit var binder: FragmentUpdatesBinding
    lateinit var mContext: Context
    private var dataInvite: MutableList<String> = ArrayList<String>()
    lateinit var updatesAdapter: UpdatesAdapter


    fun setBinder(binder: FragmentUpdatesBinding) {
        this.binder = binder
        this.mContext = binder.root.context
        binder.viewModel = this
        binder.viewClickHandler = ViewClickHandler()
        init()

    }

    fun init() {

        for (i: Int in 0..7) {
            dataInvite.add("l" + i)
        }

        updatesAdapter = UpdatesAdapter(mContext)


        updatesAdapter.addAll(dataInvite as ArrayList<String>)

        binder.rvUpdates.adapter = updatesAdapter

    }

    inner class ViewClickHandler {


    }


}


