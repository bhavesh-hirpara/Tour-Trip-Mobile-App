package com.tripkipedia.ui.notifications.invites.viewmodel

import android.app.Application
import android.content.Context
import com.tripkipedia.base.viewmodel.BaseViewModel
import com.tripkipedia.databinding.FragmentInvitesBinding
import com.tripkipedia.ui.notifications.invites.utils.InviteAdapter

/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class InvitesViewModel(application: Application) : BaseViewModel(application) {

    private lateinit var binder: FragmentInvitesBinding
    lateinit var mContext: Context
    private var dataInvite: MutableList<String> = ArrayList<String>()
    lateinit var inviteAdapter: InviteAdapter


    fun setBinder(binder: FragmentInvitesBinding) {
        this.binder = binder
        this.mContext = binder.root.context
        binder.viewModel = this
        binder.viewClickHandler = ViewClickHandler()
        init()

    }

    fun init() {

        for (i: Int in 0..9) {
            dataInvite.add("l" + i)
        }

        inviteAdapter = InviteAdapter(mContext)


        inviteAdapter.addAll(dataInvite as ArrayList<String>)

        binder.rvInvites.adapter = inviteAdapter

    }

    inner class ViewClickHandler {


    }


}


