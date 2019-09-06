package com.song2.unifarm.Adapter

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.song2.unifarm.DB.DBSearchHelper
import com.song2.unifarm.R
import com.song2.unifarm.SearchActivity
import kotlinx.android.synthetic.main.activity_search.*

class CalenderListAdapter(val ctx : Context, val calenderListData : ArrayList<String>) : RecyclerView.Adapter<CalenderListAdapter.Holder>() {

    var searchDbHelper = DBSearchHelper(ctx)
    lateinit var searchDB: SQLiteDatabase

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder{
        //뷰 인플레이트
        var view : View = LayoutInflater.from(ctx).inflate(R.layout.detailed_calender_list_item, parent, false)
        return Holder(view)
    }

    override fun getItemCount() : Int = calenderListData.size

    override fun onBindViewHolder(holder: Holder, position: Int) {

        holder.calenderListCnt.text = (position+1).toString() + "회"
        holder.calenderListData.text = calenderListData[position]
    }

    inner class Holder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val calenderListCnt : TextView = itemView.findViewById(R.id.tv_calender_list_item_cnt)
        val calenderListData : TextView = itemView.findViewById(R.id.tv_calender_list_item_date)
    }

}