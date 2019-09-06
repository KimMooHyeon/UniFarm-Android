package com.song2.unifarm

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
import java.util.Calendar
import android.support.v4.app.SupportActivity
import android.support.v4.app.SupportActivity.ExtraData
import android.support.v4.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T



class KotlinCalendar : AppCompatActivity() {
    lateinit var commingActivityRecyclerViewAdapter: CommingActivityRecyclerViewAdapter
    var CommingdataList: ArrayList<CommingActivityData> = ArrayList()

    lateinit var endActivityRecyclerViewAdapter: EndActivityRecyclerViewAdapter
    var endataList: ArrayList<EndActivityData> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)
   var     calendarView = findViewById(R.id.calendar_view) as MaterialCalendarView
       // initViews()

        calendarView.addDecorators(SundayDecorator(), SaturdayDecorator(), OneDayDecorator())
        CommingdataList.add(CommingActivityData("충북 보은 마을 재생 프로젝트","d-2","2019.09.08 토"))
        CommingdataList.add(CommingActivityData("익산시 농기계 수리 농활","d-4","2019.09.10 월"))
        CommingdataList.add(CommingActivityData("고성 초등학교 SW 멘토링","d-8","2019.09.14 금"))
        commingActivityRecyclerViewAdapter = CommingActivityRecyclerViewAdapter(this, CommingdataList)
        rv_calendar_comming.adapter = commingActivityRecyclerViewAdapter
        rv_calendar_comming.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)

        endataList.add(EndActivityData("충북 보은 마을 재생 프로젝트","d-5","2019.08.16 금"))
        endataList.add(EndActivityData("익산시 농기계 수리 농활","d-15","2019.09.16 금"))
        endataList.add(EndActivityData("고성 초등학교 SW 멘토링","d-30","2019.10.16 금"))
        endActivityRecyclerViewAdapter = EndActivityRecyclerViewAdapter(this, endataList)
        rv_calendar_end.adapter = endActivityRecyclerViewAdapter
        rv_calendar_end.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)


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
}