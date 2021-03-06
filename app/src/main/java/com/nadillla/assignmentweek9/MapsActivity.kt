package com.nadillla.assignmentweek9

import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_maps.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        //val sydney = LatLng(-34.0, 151.0)
        val bkt = LatLng(-0.3027177,100.3544704)
        val bdg = LatLng(-6.9174639,107.6191228)

        //ganti warna marker

        mMap.addMarker(MarkerOptions().position(bkt).title("Marker in Bukittinggi")
            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)))


        mMap.addMarker(MarkerOptions().position(bdg).title("Marker in Bandung").snippet("Jawa Barat")
            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)))


        mMap.moveCamera(CameraUpdateFactory.newLatLng(bkt))


        //setting zoom
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(bkt,16f))

        //setting zoom in
        mMap.uiSettings.isZoomControlsEnabled=true
        mMap.uiSettings.isCompassEnabled=true

        //type map
        btnHybrid.setOnClickListener {
            mMap.mapType=GoogleMap.MAP_TYPE_HYBRID
        }
        btnSatelite.setOnClickListener {
            mMap.mapType=GoogleMap.MAP_TYPE_SATELLITE
        }
        btnTerrain.setOnClickListener {
            mMap.mapType=GoogleMap.MAP_TYPE_TERRAIN
        }
        btnNormal.setOnClickListener {
            mMap.mapType=GoogleMap.MAP_TYPE_NORMAL
        }

        mMap.setOnMapClickListener {
            val lat = it.latitude
            val lon = it.longitude

            mMap.clear()

            val nama = convertCoorinate(lat,lon)

            textCordinate.text="$lat-$lon"
            textNamaLokasi.text=nama

            mMap.addMarker(MarkerOptions().position(LatLng(lat,lon)).title("Daerah Baru"))
            mMap.moveCamera((CameraUpdateFactory.newLatLng(LatLng(lat,lon))))

        }



    }

    fun convertCoorinate(lat : Double, lon: Double):String{
        val geocoder = Geocoder(this)
        val dataLocation = geocoder.getFromLocation(lat,lon,1)
        val nameLocation=dataLocation.get(0).getAddressLine(0)
        return nameLocation
    }
}