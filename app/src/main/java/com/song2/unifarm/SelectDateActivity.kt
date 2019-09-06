package com.song2.unifarm

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.song2.unifarm.Adapter.SelectDataListAdapter
import kotlinx.android.synthetic.main.activity_select_date.*
import org.jetbrains.anko.ctx

class SelectDateActivity : AppCompatActivity() {

    var dateData: ArrayList<String> = ArrayList<String>()

    lateinit var selectDateActivity : SelectDataListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_date)

        rl_more_btn.setOnClickListener {
            rv_select_date_act_data_list.visibility  = View.VISIBLE
        }

        setCalenderListRecyclerView()

    }

    fun setCalenderListRecyclerView(){
        dateData.add("2019. 10. 06 일요일")
        dateData.add("2019. 11. 03 화요일")
        dateData.add("2019. 11. 10 수요일")
        dateData.add("2019. 11. 13 목요일")

        selectDateActivity = SelectDataListAdapter(ctx, this, dateData)
        rv_select_date_act_data_list.adapter = selectDateActivity
        rv_select_date_act_data_list.layoutManager = LinearLayoutManager(ctx)
    }

    fun selectedItem( keyword : String){

        tv_text_setting_ment.setText("선택일")
        tv_setting_date.setText(keyword)
        rv_select_date_act_data_list.visibility  = View.GONE

        btn_select_date_act_disapear.visibility = View.GONE
        tv_select_date_act_total.setText("5,000 원")

    }
}
