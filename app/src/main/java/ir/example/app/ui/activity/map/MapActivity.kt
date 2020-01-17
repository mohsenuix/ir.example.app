package ir.example.app.ui.activity.map

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import ir.example.app.R


class MapActivity : FragmentActivity() , OnMapReadyCallback {

    private var mMap: GoogleMap? = null

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        // Add a marker in Sydney and move the camera
        val TutorialsPoint = LatLng(21.toDouble(), 57.toDouble())
        mMap?.addMarker(MarkerOptions().position(TutorialsPoint).title("Tutorialspoint.com"))
        mMap?.moveCamera(CameraUpdateFactory.newLatLng(TutorialsPoint))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)
    }
}