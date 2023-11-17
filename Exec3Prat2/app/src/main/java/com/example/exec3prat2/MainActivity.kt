package com.example.exec3prat2

import androidx.appcompat.app.AppCompatActivity
import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity(), LocationListener {

    private lateinit var locationManager: LocationManager
    private lateinit var textViewLocation: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewLocation = findViewById(R.id.strResult)
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_LOCATION_PERMISSION)
        } else {
            startLocationUpdates()
        }
    }

    private fun startLocationUpdates() {
        val isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)

        if (isGPSEnabled) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 6000, 10f, this)
            val location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)

            location?.let {
                updateLocationText(it.latitude, it.longitude)
            }
        } else {
            Toast.makeText(this, "Please enable GPS", Toast.LENGTH_LONG).show()
        }
    }

    private fun updateLocationText(latitude: Double, longitude: Double) {
        val locationInfo = "Latitude: $latitude\nLongitude: $longitude"
        textViewLocation.text = locationInfo
    }

    override fun onLocationChanged(location: Location) {
        updateLocationText(location.latitude, location.longitude)
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
    }

    override fun onProviderEnabled(provider: String) {
    }

    override fun onProviderDisabled(provider: String) {
    }

    companion object {
        private const val REQUEST_LOCATION_PERMISSION = 1
    }
}

