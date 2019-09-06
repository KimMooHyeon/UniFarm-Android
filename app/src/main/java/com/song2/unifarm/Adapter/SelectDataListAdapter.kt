package com.song2.unifarm.Adapter

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.song2.unifarm.DB.DBSearchHelper
import com.song2.unifarm.R
import com.song2.unifarm.SearchActivity
import com.song2.unifarm.SelectDateActivity
import kotlinx.android.synthetic.main.activity_search.*

class SelectDataListAdapter(val ctx : Context,val selectDateActivity: SelectDateActivity , val calenderListData : ArrayList<String>) : RecyclerView.Adapter<SelectDataListAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder{
        //뷰 인플레이트
        var view : View = LayoutInflater.from(ctx).inflate(R.layout.select_data_item, parent, false)
        return Holder(view)
    }

    override fun getItemCount() : Int = calenderListData.size

    override fun onBindViewHolder(holder: Holder, position: Int) {

        holder.container.setOnClickListener {
            selectDateActivity.selectedItem(calenderListData[position],position)
        }
        holder.calenderListData.text = calenderListData[position]
    }

    inner class Holder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val container : LinearLayout = itemView.findViewById(R.id.ll_select_data_item_container)
        val calenderListData : TextView = itemView.findViewById(R.id.tv_select_data_item_date)
    }

}