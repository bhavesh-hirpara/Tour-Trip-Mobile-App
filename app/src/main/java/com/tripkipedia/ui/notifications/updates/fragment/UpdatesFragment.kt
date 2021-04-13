package com.tripkipedia.ui.notifications.updates.fragment

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.tripkipedia.R
import com.tripkipedia.base.fragment.BaseFragment
import com.tripkipedia.databinding.*
import com.tripkipedia.ui.dashboard.features.viewmodel.FeaturesViewModel
import com.tripkipedia.ui.dashboard.home.viewmodel.HomeViewModel
import com.tripkipedia.ui.freebiesDetails.details.viewmodel.DetailsViewModel
import com.tripkipedia.ui.notifications.invites.viewmodel.InvitesViewModel
import com.tripkipedia.ui.notifications.updates.viewmodel.UpdatesViewModel

/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class UpdatesFragment : BaseFragment() {

    private var updatesViewModel: UpdatesViewModel? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentUpdatesBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_updates, container, false
        )
        updatesViewModel = ViewModelProvider(this).get(UpdatesViewModel::class.java)
        updatesViewModel!!.setBinder(binding)
        val view: View = binding.getRoot()
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

}