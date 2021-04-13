package com.tripkipedia.ui.myTickets.current.fragment

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.tripkipedia.R
import com.tripkipedia.base.fragment.BaseFragment
import com.tripkipedia.databinding.*
import com.tripkipedia.ui.myTickets.current.viewmodel.CurrentViewModel

/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class CurrentFragment : BaseFragment() {

    private var currentViewModel: CurrentViewModel? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentCurrentBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_current, container, false
        )
        currentViewModel = ViewModelProvider(this).get(CurrentViewModel::class.java)
        currentViewModel!!.setBinder(binding)
        val view: View = binding.getRoot()
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

}