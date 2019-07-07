package com.linkdev.demokotlin.ui.location

import android.annotation.SuppressLint
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.content.Context
import android.location.Location
import android.util.Log
import android.view.View
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.linkdev.demokotlin.R
import com.linkdev.demokotlin.common.helpers.SnackbarHelper
import com.linkdev.demokotlin.common.helpers.Utils.checkEnableGPS
import com.linkdev.demokotlin.ui.base.PermissionHandlerFragment
import kotlinx.android.synthetic.main.fragment_main.*


class LocationFragment : PermissionHandlerFragment(), OnMapReadyCallback {
    private val permissionsLocation =
        arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION)

    companion object {
        private const val REQUEST_LOCATION_CODE = 101
        private const val REQUEST_CHECK_SETTINGS = 101
        const val TAG = "LocationFragment"
        fun newInstance() = LocationFragment()
    }

    private var locationViewModel: LocationViewModel? = null
    private var mCurrLocationMarker: Marker? = null
    private lateinit var mMap: GoogleMap

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
    }

    override fun onViewReady(context: Context) {
        initViewModel()
        setObservers()
    }

    override fun layoutViewId(): Int {
        return com.linkdev.demokotlin.R.layout.fragment_location
    }

    override fun setListeners() {
    }

    override fun setObservers() {
        locationViewModel?.getOnSuccessLoadLocation()?.observe(this, locationOnSuccessObserver)
        locationViewModel?.getErrorObserver()?.observe(this, onErroeObserver)
        locationViewModel?.getLoadingObserver()?.observe(this, loadingObserver)


    }

    override fun showProgress(shouldShow: Boolean) {
        if (shouldShow) {
            loadView.visibility = View.VISIBLE
        } else {
            loadView.visibility = View.GONE
        }
    }

    override fun onPermissionDenied(codePermission: Int) {
    }

    override fun onResume() {
        super.onResume()
        if (checkEnableGPS(activity,REQUEST_CHECK_SETTINGS))
            checkPermissions(context, REQUEST_LOCATION_CODE, *permissionsLocation)
    }

    @SuppressLint("MissingPermission")
    override fun onPermissionGranted(codePermission: Int) {
        if (codePermission == REQUEST_LOCATION_CODE) {
            locationViewModel?.requestMyLocation()
        }
    }

    override fun initializeViews(v: View) {
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    private fun initViewModel() {
        val locationViewModelFactory = LocationViewModelFactory(activity!!.application)
        locationViewModel = ViewModelProviders.of(this, locationViewModelFactory).get(LocationViewModel::class.java)

    }

    private var onErroeObserver = Observer<Int> {
        Log.d("onErroeObserver", "" + it)
        if (context != null && it != null && view != null) {
            SnackbarHelper.showErrorMessage(context!!, view!!, it)
        }
    }
    private var locationOnSuccessObserver = Observer<Location> {
        mCurrLocationMarker?.remove()
        val latLng = LatLng(it!!.latitude, it.longitude)
        val markerOptions = MarkerOptions()
        markerOptions.position(latLng)
        markerOptions.title(getString(R.string.myLocation))
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
        mCurrLocationMarker = mMap.addMarker(markerOptions)
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
    }
    private var loadingObserver = Observer<Boolean> {
        Log.d("loadingObserver", "" + it)
        if (it != null)
            showProgress(it)
    }
}
