package com.song2.unifarm

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.bumptech.glide.Glide
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.song2.unifarm.Adapter.CalenderListAdapter
import com.song2.unifarm.Network.ApplicationController
import com.song2.unifarm.Network.GET.GetDetaliedResponse
import com.song2.unifarm.Network.GET.ProgramData
import com.song2.unifarm.Network.GET.ProgramDate
import com.song2.unifarm.Network.NetworkService
import kotlinx.android.synthetic.main.activity_detailed.*
import org.jetbrains.anko.ctx
import org.jetbrains.anko.startActivity
import retrofit2.Call
import retrofit2.Response


class DetailedActivity : AppCompatActivity(), OnMapReadyCallback {

    lateinit var networkService: NetworkService

    var dateData: ArrayList<String> = ArrayList<String>()
    var programdate_ : ArrayList<ProgramDate> = ArrayList<ProgramDate>()

    lateinit var calenderListAdapter: CalenderListAdapter

    lateinit var mapView: MapView

    var idxxx = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed)


        idxxx = intent.getIntExtra("idxxx",1)
        networkService = ApplicationController.instance.networkService

        getDetailedResponse()

        mapView = findViewById(R.id.fragment_detailed_act_location_map)
        mapView.onCreate(savedInstanceState)

        mapView.getMapAsync(this)

        rl_detailed_act_apply_btn.setOnClickListener {
            startActivity<SelectDateActivity>()
            Log.e("rl_detailed_act_apply_btn",programdate_.toString())
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

    fun setCalenderListRecyclerView(programDates : ArrayList<ProgramDate>){

        for(i in programDates.indices){
            dateData.add(programDates[i].startDate + " - " + programDates[i].endDate)
        }

        calenderListAdapter = CalenderListAdapter(ctx, dateData)
        rv_detailed_act_calender_list.adapter = calenderListAdapter
        rv_detailed_act_calender_list.layoutManager = LinearLayoutManager(ctx)
    }

    fun getDetailedResponse() {
        val getHitsResponse = networkService.getDetailedResponse(
            "application/json",
            "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ1bmlmYXJtIiwidXNlcl9JZHgiOjl9.2FguegbBxWfn_MvGHkdQzpssoBXh-GWvQQInVZBuZgE",idxxx)

        getHitsResponse.enqueue(object : retrofit2.Callback<GetDetaliedResponse> {
            override fun onFailure(call: Call<GetDetaliedResponse>, t: Throwable) {
                Log.e("getHitsResponse fail", t.toString())
            }

            override fun onResponse(call: Call<GetDetaliedResponse>, response: Response<GetDetaliedResponse>) {
                if (response.isSuccessful) {
                    val programData: ProgramData = response.body()!!.data
                    Log.e("programData success",programData.toString())

                    if (programData != null) {
                        setProgramData(programData)
                    }
                }
            }
        })
    }

    fun setProgramData(programData:ProgramData){

        if(programData.program != null){
            //program
            tv_detailed_act_title.setText(programData.program.title)
            tv_detailed_act_contents.setText(programData.program.body)
            tv_detailed_act_sub_title.setText((programData.program.subTitle))

            Glide.with(ctx).load(programData.program.thumbnail).into(iv_detailed_act_title_img)
            tv_detailed_act_location.setText(programData.program.address)
            tv_detailed_act_entry.setText(programData.program.target)

            tv_detailed_act_detailed_cnt.setText(programData.program.maxNumber)
            tv_detailed_act_money.setText(programData.program.cost + " 원")

            tv_detailed_act_reward.setText(programData.program.reward)

            tv_detailed_act_question.setText(programData.program.ask)
            //tv_detailed_act_call.setText(programData.program.regiNumber.toString())
            Log.e("데이터확인해야겠다",programData.program.toString())
        }

        if(programData.programDates != null) {
            //programDates
            setCalenderListRecyclerView(programData.programDates)
            programdate_ = programData.programDates
        }

        if(programData.keywords != null) {
            //keywords
            tv_detailed_act_keyword0.setText("#"+programData.keywords[0].info)
            tv_detailed_act_keyword1.setText("#"+programData.keywords[1].info)
            tv_detailed_act_keyword2.setText("#"+programData.keywords[2].info)
            tv_detailed_act_keyword3.setText("# "+programData.keywords[3].info)
            tv_detailed_act_keyword4.setText("# "+programData.keywords[4].info)
        }
    }


}
