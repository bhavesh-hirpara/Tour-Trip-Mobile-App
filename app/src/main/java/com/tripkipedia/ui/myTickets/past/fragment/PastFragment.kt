package com.tripkipedia.ui.myTickets.past.fragment

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.tripkipedia.R
import com.tripkipedia.base.fragment.BaseFragment
import com.tripkipedia.databinding.*
import com.tripkipedia.ui.myTickets.past.viewmodel.PastViewModel

/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class PastFragment : BaseFragment() {

    private var pastViewModel: PastViewModel? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentPastBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_past, container, false
        )
        pastViewModel = ViewModelProvider(this).get(PastViewModel::class.java)
        pastViewModel!!.setBinder(binding)
        val view: View = binding.getRoot()
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

}