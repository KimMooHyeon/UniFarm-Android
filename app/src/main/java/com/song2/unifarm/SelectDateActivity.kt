package com.song2.unifarm

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.song2.unifarm.Adapter.CalenderListAdapter
import com.song2.unifarm.Adapter.SelectDataListAdapter
import com.song2.unifarm.Network.ApplicationController
import com.song2.unifarm.Network.GET.GetDetaliedResponse
import com.song2.unifarm.Network.GET.ProgramData
import com.song2.unifarm.Network.GET.ProgramDate
import com.song2.unifarm.Network.NetworkService
import com.song2.unifarm.Network.POST.PostProgram
import com.song2.unifarm.Network.POST.PostResponse
import kotlinx.android.synthetic.main.activity_detailed.*
import kotlinx.android.synthetic.main.activity_select_date.*
import org.jetbrains.anko.ctx
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Response

class SelectDateActivity : AppCompatActivity() {

    var cost = "0"
    var isIn = 0
    lateinit var pp : PostProgram
    lateinit var networkService: NetworkService
    lateinit var programDate_ : ArrayList<ProgramDate>
    var dateData: ArrayList<String> = ArrayList<String>()

    lateinit var selectDateActivity : SelectDataListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_date)

        networkService = ApplicationController.instance.networkService

        getDetailedResponse()
        rl_more_btn.setOnClickListener {
            rv_select_date_act_data_list.visibility  = View.VISIBLE
        }

        rl_select_date_act_apply_btn.setOnClickListener {
            if(isIn ==1){
                startActivity<CompleteActivity>()
                postProgramResponse()
            }else{
                toast("회차를 선택 해 주세요!")
            }

        }

    }

    fun postProgramResponse(){

        var postLoginResponse = networkService.postProgramResponse("application/json",
            "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ1bmlmYXJtIiwidXNlcl9JZHgiOjl9.2FguegbBxWfn_MvGHkdQzpssoBXh-GWvQQInVZBuZgE", pp)
        postLoginResponse.enqueue(object : retrofit2.Callback<PostResponse>{

            override fun onResponse(call: Call<PostResponse>, response: Response<PostResponse>) {
                if(response.isSuccessful){
                    Log.v("Asdf","승인 = " + response.body())
                }
            }
            override fun onFailure(call: Call<PostResponse>, t: Throwable?) {
                Log.e("에러에러",t.toString())
            }
        })

    }

    fun setCalenderListRecyclerView(programDates : ArrayList<ProgramDate>){

        for(i in programDates.indices){
            dateData.add(programDates[i].startDate + " - " + programDates[i].endDate)
        }

        selectDateActivity = SelectDataListAdapter(ctx,this, dateData)
        rv_select_date_act_data_list.adapter = selectDateActivity
        rv_select_date_act_data_list.layoutManager = LinearLayoutManager(ctx)
    }

    fun selectedItem( keyword : String, idx : Int){

        tv_text_setting_ment.setText("선택일")
        tv_setting_date.setText(keyword)
        rv_select_date_act_data_list.visibility  = View.GONE

        isIn = 1
        pp = PostProgram(programDate_[idx].programIdx,programDate_[idx].startDate,programDate_[idx].endDate)

        btn_select_date_act_disapear.visibility = View.GONE
        tv_select_date_act_total.setText(cost+" 원")

    }

    fun getDetailedResponse() {
        val getHitsResponse = networkService.getDetailedResponse(
            "application/json",
            "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ1bmlmYXJtIiwidXNlcl9JZHgiOjl9.2FguegbBxWfn_MvGHkdQzpssoBXh-GWvQQInVZBuZgE",1)

        getHitsResponse.enqueue(object : retrofit2.Callback<GetDetaliedResponse> {
            override fun onFailure(call: Call<GetDetaliedResponse>, t: Throwable) {
                Log.e("getHitsResponse fail select", t.toString())
            }

            override fun onResponse(call: Call<GetDetaliedResponse>, response: Response<GetDetaliedResponse>) {
                if (response.isSuccessful) {
                    val programData: ProgramData = response.body()!!.data
                    Log.e("programData success select",programData.toString())

                    if (programData != null) {

                        cost = programData.program.cost
                        if(programData.programDates != null) {
                            programDate_ = programData.programDates
                            setCalenderListRecyclerView(programData.programDates)
                        }

                    }
                }
            }
        })
    }

}
