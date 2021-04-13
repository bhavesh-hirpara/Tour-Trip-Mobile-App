package com.tripkipedia.apputils

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import java.text.DateFormat
import java.util.*

/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class LocationServiceImpl : LocationListener,GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

  override fun onStatusChanged(p0: String?,p1: Int,p2: Bundle?) {
  }

  override fun onProviderEnabled(p0: String?) {

  }

  override fun onProviderDisabled(p0: String?) {
  }

  lateinit var mLocationRequest: LocationRequest
  lateinit var mGoogleApiClient: GoogleApiClient
  private lateinit var locationCallback: LocationCallback
  var mCurrentLocation: Location? = null
  lateinit var mLastUpdateTime: String

  var isOneTime: Boolean = false

  lateinit var context: Context
  var token: String


  internal var hasClientConfig = false
  internal var interval = (9 * 1000).toLong()
  internal var fastestInterval = (5 * 1000).toLong()

  internal var smallestDisplacementMeters = 10f

  constructor(token: String) {
    this.token = token
  }

  private//            GooglePlayServicesUtil.getErrorDialog(status, context, 0).show();
  val isGooglePlayServicesAvailable: Boolean
    get() {
      val status = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context)
      return ConnectionResult.SUCCESS == status
    }

  protected fun createLocationRequest() {
    mLocationRequest = LocationRequest()

    if (hasClientConfig) {
      mLocationRequest.setInterval(interval)
    } else {
      mLocationRequest.setInterval(INTERVAL)
    }

    if (hasClientConfig) {
      mLocationRequest.setFastestInterval(fastestInterval)
    } else {
      mLocationRequest.setFastestInterval(FASTEST_INTERVAL)
    }
    locationCallback = object : LocationCallback() {
      override fun onLocationResult(locationResult: LocationResult?) {
        locationResult ?: return
        for (location in locationResult.locations) {
          // Update UI with location data
          // ...
          mCurrentLocation = location
          mLastUpdateTime = DateFormat.getTimeInstance().format(Date())
          Utils.setLatLong(context, location.longitude.toFloat(), location.latitude.toFloat())
          val intent = Intent(Constant.LOCATION_UPDATED)
          intent.putExtra(TOKEN,"" + token)
          LocalBroadcastManager.getInstance(context).sendBroadcast(intent)
          updateUI()
          if (isOneTime) {
            stopLocationUpdates()
            stop()
          }
        }
      }
    }

    mLocationRequest.smallestDisplacement = smallestDisplacementMeters
    mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
  }

  fun setInterval(interval: Long) {
    hasClientConfig = true
    this.interval = interval
  }

  fun setFastestInterval(fastestInterval: Long) {
    hasClientConfig = true
    this.fastestInterval = fastestInterval
  }

  fun init(context: Context) {
    this.context = context

    //show error dialog if GoolglePlayServices not available
    if (!isGooglePlayServicesAvailable) {
      return
    }

    createLocationRequest()
    mGoogleApiClient = GoogleApiClient.Builder(context)
            .addApi(LocationServices.API)
            .addConnectionCallbacks(this)
            .addOnConnectionFailedListener(this)
            .build()

    mGoogleApiClient.connect()
  }

  fun stop() {
    mGoogleApiClient.disconnect()
  }

  override fun onConnected(bundle: Bundle?) {
    startLocationUpdates()
  }

  protected fun startLocationUpdates() {
    if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
    ) {
      return
    }
    LocationServices.getFusedLocationProviderClient(context).requestLocationUpdates(mLocationRequest,
            locationCallback,
            null)
  }


  override fun onConnectionSuspended(i: Int) {

  }

  override fun onConnectionFailed(connectionResult: ConnectionResult) {
  }

  override fun onLocationChanged(location: Location) {
    mCurrentLocation = location
    mLastUpdateTime = DateFormat.getTimeInstance().format(Date())
    Utils.setLatLong(context, location.longitude.toFloat(), location.latitude.toFloat())
    val intent = Intent(Constant.LOCATION_UPDATED)
    intent.putExtra(TOKEN,"" + token)
    LocalBroadcastManager.getInstance(context).sendBroadcast(intent)
    updateUI()
    if (isOneTime) {
      stopLocationUpdates()
      stop()
    }
  }

  private fun updateUI() {
    if (null != mCurrentLocation) {
      val lat = mCurrentLocation!!.latitude.toString()
      val lng = mCurrentLocation!!.longitude.toString()
      Debug.e(
              "", "At Time: " + mLastUpdateTime + "\n" +
              "Latitude: " + lat + "\n" +
              "Longitude: " + lng + "\n" +
              "Accuracy: " + mCurrentLocation!!.accuracy + "\n" +
              "Provider: " + mCurrentLocation!!.provider
      )
    } else {
      Debug.e(TAG, "location is null ...............")
    }
  }

  fun stopLocationUpdates() {
    LocationServices.getFusedLocationProviderClient(context).removeLocationUpdates(locationCallback)
    Debug.d(TAG, "Location update stopped .......................")
  }

  companion object {

    private val TAG = "LocationActivity"
    private val INTERVAL = (60 * 60 * 1000).toLong()
    private val FASTEST_INTERVAL = (1000 * 60).toLong()

    val TOKEN = "token"
  }
}

