package com.example.recyclingwiki

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.example.recyclingwiki.databinding.ActivityGooglemapBinding
import com.example.recyclingwiki.databinding.ActivityMainBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_map.*
import java.util.*
import java.util.jar.Manifest

class Googlemap : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_googlemap)

        val binding = ActivityGooglemapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        findViewById<LinearLayout>(R.id.card_view).visibility=View.GONE

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    override fun onMapReady(googleMap: GoogleMap) {

        mMap = googleMap



        val center =LatLng(37.625598,127.072954)
        val seoultech = LatLng(37.631600, 127.077465)
        val office1 = LatLng(37.624676,127.073779)
        val office2 = LatLng(37.621243,127.083394)
        val market = LatLng(37.623990,127.073187)

        val marker1 = MarkerOptions().position(seoultech).title("office").snippet("02-970-0001")
        mMap.addMarker(marker1)
        val marker2 = MarkerOptions().position(office1).title("icepack").snippet("02-970-0002")
        mMap.addMarker(marker2)
        val marker3 = MarkerOptions().position(office2).title("나눔의집").snippet("02-970-0003")
        mMap.addMarker(marker3)
        val marker4 = MarkerOptions().position(market).title("flea market").snippet("02-970-0004")
        mMap.addMarker(marker4)

        mMap.moveCamera(CameraUpdateFactory.newLatLng(LatLng(37.625598,127.072954)))
        mMap.moveCamera(CameraUpdateFactory.zoomTo(15F))

        googleMap!!.setOnMarkerClickListener(object : GoogleMap.OnMarkerClickListener {
            override fun onMarkerClick(marker: Marker): Boolean {
                findViewById<View>(R.id.card_view).visibility = View.VISIBLE
                var parkname = findViewById<TextView>(R.id.park_name)
                var parkwhat = findViewById<TextView>(R.id.park_what)
//                var parkadd2 = findViewById<TextView>(R.id.park_add_road)
                parkname.text = marker.title
                parkwhat.text = marker.snippet
                findViewById<TextView>(R.id.main_name).text = marker.title
//                Log.d("parkinfo", "parkname->"+marker.title+"___pakrwhat->")
                return false
            }
        })
        googleMap!!.setOnMapClickListener(object : GoogleMap.OnMapClickListener {
            override fun onMapClick(latLng: LatLng) {
                findViewById<View>(R.id.card_view).visibility = View.GONE
            }
        })
    }


}
