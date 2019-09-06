package com.song2.unifarm

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.song2.unifarm.Adapter.CalenderListAdapter
import kotlinx.android.synthetic.main.activity_detailed.*
import org.jetbrains.anko.ctx
import org.jetbrains.anko.startActivity


class DetailedActivity : AppCompatActivity(), OnMapReadyCallback {

    var dateData: ArrayList<String> = ArrayList<String>()

    lateinit var calenderListAdapter: CalenderListAdapter

    lateinit var mapView: MapView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed)

        setCalenderListRecyclerView()

        mapView = findViewById(R.id.fragment_detailed_act_location_map)
        mapView.onCreate(savedInstanceState)

        mapView.getMapAsync(this)

        rl_detailed_act_apply_btn.setOnClickListener {
            startActivity<SelectDateActivity>()
        }

    }

    override fun onMapReady(googleMap: GoogleMap?) {

        MapsInitializer.initialize(ctx)

        val cameraUpdate = CameraUpdateFactory.newLatLngZoom(LatLng(37.56, 126.97), 14f)

        googleMap!!.animateCamera(cameraUpdate)

        googleMap.addMarker(
            MarkerOptions()
                .position(LatLng(37.56, 126.97))
                .title("서울")
        )

        googleMap.addCircle(
            CircleOptions()
                .radius(50.0)
                .strokeWidth(0f)
                .center(LatLng(37.56, 126.97))
                .fillColor(Color.parseColor("#ffe674"))
        )
    }

    fun setCalenderListRecyclerView(){
        dateData.add("2019. 10. 06 일요일")
        dateData.add("2019. 11. 03 화요일")
        dateData.add("2019. 11. 10 수요일")
        dateData.add("2019. 11. 13 목요일")

        calenderListAdapter = CalenderListAdapter(ctx, dateData)
        rv_detailed_act_calender_list.adapter = calenderListAdapter
        rv_detailed_act_calender_list.layoutManager = LinearLayoutManager(ctx)
    }
}
