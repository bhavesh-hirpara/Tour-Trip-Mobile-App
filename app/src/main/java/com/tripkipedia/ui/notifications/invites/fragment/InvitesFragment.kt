package com.tripkipedia.ui.notifications.invites.fragment

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.tripkipedia.R
import com.tripkipedia.base.fragment.BaseFragment
import com.tripkipedia.databinding.FragmentDetailsBinding
import com.tripkipedia.databinding.FragmentFeaturesBinding
import com.tripkipedia.databinding.FragmentHomeBinding
import com.tripkipedia.databinding.FragmentInvitesBinding
import com.tripkipedia.ui.dashboard.features.viewmodel.FeaturesViewModel
import com.tripkipedia.ui.dashboard.home.viewmodel.HomeViewModel
import com.tripkipedia.ui.freebiesDetails.details.viewmodel.DetailsViewModel
import com.tripkipedia.ui.notifications.invites.viewmodel.InvitesViewModel

/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class InvitesFragment : BaseFragment() {

    private var invitesViewModel: InvitesViewModel? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentInvitesBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_invites, container, false
        )
        invitesViewModel = ViewModelProvider(this).get(InvitesViewModel::class.java)
        invitesViewModel!!.setBinder(binding)
        val view: View = binding.getRoot()
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

}