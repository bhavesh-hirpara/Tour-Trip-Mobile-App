package com.tripkipedia.ui.freebiesDetails.details.fragment

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.tripkipedia.R
import com.tripkipedia.base.fragment.BaseFragment
import com.tripkipedia.databinding.FragmentDetailsBinding
import com.tripkipedia.ui.freebiesDetails.details.viewmodel.DetailsViewModel

/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class DetailsFragment : BaseFragment() {

    private var detailsViewModel: DetailsViewModel? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentDetailsBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_details, container, false
        )
        detailsViewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)
        val args = arguments
        val itemId = args?.getString("itemId")
        detailsViewModel!!.setBinder(binding,itemId)
        val view: View = binding.getRoot()
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

}