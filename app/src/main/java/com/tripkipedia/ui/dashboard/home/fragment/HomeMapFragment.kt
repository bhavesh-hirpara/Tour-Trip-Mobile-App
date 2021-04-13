package com.tripkipedia.ui.dashboard.home.fragment

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.location.Location
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.tripkipedia.R
import com.tripkipedia.apputils.Debug
import com.tripkipedia.apputils.Utils
import com.tripkipedia.base.fragment.BaseFragment
import com.tripkipedia.databinding.FragmentHomeBinding
import com.tripkipedia.ui.dashboard.home.viewmodel.HomeViewModel

/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class HomeMapFragment : BaseFragment() {

    private var homeViewModel: HomeViewModel? = null
    private var mContext: Context? = null
    var gMap: GoogleMap? = null

    val hashMap = HashMap<LatLng, Int>()

    val g1 = LatLng(22.947458, 71.797284)
    val g2 = LatLng(33.347505, 76.631247)
    val g3 = LatLng(28.611635, 77.308877)
    val g4 = LatLng(22.437353, 76.319602)
    val g5 = LatLng(24.140748, 86.980401)
    val g6 = LatLng(19.173568, 75.045211)
    val g7 = LatLng(11.286549, 78.013421)
    val g8 = LatLng(9.092318, 77.572634)
    val g9 = LatLng(19.756301, 81.341586)
    val g10 = LatLng(27.323001, 73.079288)


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentHomeBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_home, container, false
        )


        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        homeViewModel!!.setBinder(binding,childFragmentManager)
        val view: View = binding.getRoot()
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




    }



}