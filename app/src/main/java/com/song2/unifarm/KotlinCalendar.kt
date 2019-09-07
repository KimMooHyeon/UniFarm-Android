package com.song2.unifarm

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.RadioGroup
import android.widget.Toast
import com.applikeysolutions.cosmocalendar.utils.SelectionType
import com.applikeysolutions.cosmocalendar.view.CalendarView
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.song2.unifarm.Adapter.CommingActivityRecyclerViewAdapter
import com.song2.unifarm.Adapter.EndActivityRecyclerViewAdapter
import com.song2.unifarm.Adapter.RecommendProgramRecyclerViewAdapter
import com.song2.unifarm.Data.CollectPopularProgramData
import com.song2.unifarm.Data.CommingActivityData
import com.song2.unifarm.Data.EndActivityData
import com.song2.unifarm.Data.RecommendCollectViewData
import kotlinx.android.synthetic.main.activity_calendar.*
import kotlinx.android.synthetic.main.activity_collect_view.*
import java.text.SimpleDateFormat
import android.support.v4.app.SupportActivity
import android.support.v4.app.SupportActivity.ExtraData
import android.support.v4.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T

import android.support.v4.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.prolificinteractive.materialcalendarview.CalendarDay

import android.support.v4.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.util.Log
import com.song2.unifarm.Adapter.CollectViewPopularRecyclerViewAdapter
import com.song2.unifarm.Network.ApplicationController
import com.song2.unifarm.Network.NetworkService
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList


class KotlinCalendar : AppCompatActivity() {
    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }

    lateinit var commingActivityRecyclerViewAdapter: CommingActivityRecyclerViewAdapter
    var CommingdataList: ArrayList<CommingActivityData> = ArrayList()

    lateinit var endActivityRecyclerViewAdapter: EndActivityRecyclerViewAdapter
    var endataList: ArrayList<EndActivityData> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)
        // initViews()




        iv_collect_view_home_1.setOnClickListener {
            finish()
        }
        getUserProgramResponse()
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    /*  override fun onOptionsItemSelected(item: MenuItem): Boolean {
          when (item.itemId) {

              R.id.clear_selections -> {
                  calendarView!!.clearSelections()
                  return true
              }

              R.id.show_selections -> {
                  val days = calendarView!!.selectedDates

                  var result = ""
                  for (i in days.indices) {
                      val calendar = days[i]
                      val day = calendar.get(Calendar.DAY_OF_MONTH)
                      val month = calendar.get(Calendar.MONTH)
                      val year = calendar.get(Calendar.YEAR)
                      val week = SimpleDateFormat("EE").format(calendar.time)
                      val day_full =
                          year.toString() + "년 " + (month + 1) + "월 " + day + "일 " + week + "요일"
                      result += day_full + "\n"
                  }
                  Toast.makeText(this, result, Toast.LENGTH_LONG).show()
                  return true
              }

              else -> return super.onOptionsItemSelected(item)
          }
      }*/
    fun getUserProgramResponse(){
        val getUserProgramResponse = networkService.getUserProgram(
            "application/json",
            "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ1bmlmYXJtIiwidXNlcl9JZHgiOjl9.2FguegbBxWfn_MvGHkdQzpssoBXh-GWvQQInVZBuZgE")

        getUserProgramResponse.enqueue(object : retrofit2.Callback<GetUserProgramResponse> {
            override fun onFailure(call: Call<GetUserProgramResponse>, t: Throwable) {
                Log.e("getProgramsMajorResponse fail", t.toString())
            }

            override fun onResponse(call: Call<GetUserProgramResponse>, response: Response<GetUserProgramResponse>) {
                if (response.isSuccessful) {
                    endataList= response.body()!!.data.programEnd
                    CommingdataList=response.body()!!.data.programIng
                    commingActivityRecyclerViewAdapter = CommingActivityRecyclerViewAdapter(this@KotlinCalendar, CommingdataList)
                    rv_calendar_comming.adapter = commingActivityRecyclerViewAdapter
                    rv_calendar_comming.layoutManager = LinearLayoutManager(this@KotlinCalendar, LinearLayoutManager.VERTICAL, false)
                    endActivityRecyclerViewAdapter = EndActivityRecyclerViewAdapter(this@KotlinCalendar, endataList)
                    rv_calendar_end.adapter = endActivityRecyclerViewAdapter
                    rv_calendar_end.layoutManager = LinearLayoutManager(this@KotlinCalendar, LinearLayoutManager.VERTICAL, false)

                    var calendarView = findViewById(R.id.calendar_view) as MaterialCalendarView
                    calendarView.addDecorators(SundayDecorator(), SaturdayDecorator(), OneDayDecorator())
                    var dates: ArrayList<CalendarDay> = ArrayList();

                    for(i in 0..response.body()!!.data.programIng.size-1) {
                        dates.add(CalendarDay.from(Integer.parseInt(response.body()!!.data!!.programIng!![i].startDate!!.slice(IntRange(0,3))), Integer.parseInt(response.body()!!.data!!.programIng!![i].startDate!!.slice(IntRange(6,6)))-1, Integer.parseInt(response.body()!!.data!!.programIng!![i].startDate!!.slice(IntRange(8,9)))))
                      }





                    calendarView.addDecorator(EventDecorator(Color.RED, dates, this@KotlinCalendar))

                }
            }
        })
    }
}