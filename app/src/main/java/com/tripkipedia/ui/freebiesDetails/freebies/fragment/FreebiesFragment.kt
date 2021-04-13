package com.tripkipedia.ui.freebiesDetails.freebies.fragment

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.tripkipedia.R
import com.tripkipedia.base.fragment.BaseFragment
import com.tripkipedia.databinding.FragmentDetailsBinding
import com.tripkipedia.databinding.FragmentFeaturesBinding
import com.tripkipedia.databinding.FragmentFreebiesBinding
import com.tripkipedia.databinding.FragmentHomeBinding
import com.tripkipedia.ui.dashboard.features.viewmodel.FeaturesViewModel
import com.tripkipedia.ui.dashboard.home.viewmodel.HomeViewModel
import com.tripkipedia.ui.freebiesDetails.details.viewmodel.DetailsViewModel
import com.tripkipedia.ui.freebiesDetails.freebies.viewmodel.FreebiesViewModel

/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class FreebiesFragment : BaseFragment() {

    private var freebiesViewModel: FreebiesViewModel? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentFreebiesBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_freebies, container, false
        )
        freebiesViewModel = ViewModelProvider(this).get(FreebiesViewModel::class.java)
        freebiesViewModel!!.setBinder(binding)
        val view: View = binding.getRoot()
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

}