package com.tripkipedia.ui.dashboard.features.fragment

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.tripkipedia.R
import com.tripkipedia.base.fragment.BaseFragment
import com.tripkipedia.databinding.FragmentFeaturesBinding
import com.tripkipedia.ui.dashboard.features.viewmodel.FeaturesViewModel

/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class HomeListFragment : BaseFragment() {

    private var featuresViewModel: FeaturesViewModel? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentFeaturesBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_features, container, false
        )
        featuresViewModel = ViewModelProvider(this).get(FeaturesViewModel::class.java)
        featuresViewModel!!.setBinder(binding)
        val view: View = binding.getRoot()
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}