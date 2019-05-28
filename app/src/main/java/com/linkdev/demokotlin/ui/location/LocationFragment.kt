package com.linkdev.demokotlin.ui.location

import android.Manifest
import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.location.Location
import android.location.LocationManager
import android.provider.Settings
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.google.android.gms.location.LocationListener
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.linkdev.demokotlin.R
import com.linkdev.demokotlin.ui.base.PermissionHandlerFragment

class LocationFragment : PermissionHandlerFragment(), OnMapReadyCallback, LocationListener {

    companion object {
        private const val REQUEST_LOCATION_CODE = 101
        const val TAG = "LocationFragment"
        @JvmStatic
        fun newInstance() = LocationFragment()
    }

    private lateinit var locationViewModel: LocationViewModel

    private var service: LocationManager? = null
    private var enabled: Boolean? = null

    private var mCurrLocationMarker: Marker? = null
    private lateinit var mMap: GoogleMap

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.mapType = GoogleMap.MAP_TYPE_NORMAL

    }

    override fun onViewReady(context: Context) {
        initViewModel()
        checkPermissions(context, REQUEST_LOCATION_CODE, Manifest.permission.ACCESS_FINE_LOCATION)

    }

    override fun layoutViewId(): Int {
        return R.layout.fragment_location
    }

    override fun setListeners() {
    }

    override fun setObservers() {
    }

    override fun showProgress(shouldShow: Boolean) {
    }

    override fun onPermissionDenied(codePermission: Int) {
        val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
        startActivity(intent)
    }

    @SuppressLint("MissingPermission")
    override fun onPermissionGranted(codePermission: Int) {
        locationViewModel.buildGoogleApiClient()
    }

    override fun initializeViews(v: View) {
        service = context!!.getSystemService(AppCompatActivity.LOCATION_SERVICE) as LocationManager
        enabled = service!!.isProviderEnabled(LocationManager.GPS_PROVIDER)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    private fun initViewModel() {
        val locationViewModelFactory = LocationViewModelFactory(activity!!.application, this)
        locationViewModel = ViewModelProviders.of(this, locationViewModelFactory).get(LocationViewModel::class.java)

    }

    override fun onLocationChanged(location: Location?) {
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker!!.remove()
        }
        val latLng = LatLng(location!!.latitude, location.longitude)
        val markerOptions = MarkerOptions()
        markerOptions.position(latLng)
        markerOptions.title(getString(R.string.myLocation))
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
        mCurrLocationMarker = mMap.addMarker(markerOptions)
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
    }

}
