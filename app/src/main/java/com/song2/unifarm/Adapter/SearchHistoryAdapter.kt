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

class SearchHistoryAdapter(val ctx : Context, val searchActivity: SearchActivity, val searchData : ArrayList<String>) : RecyclerView.Adapter<SearchHistoryAdapter.Holder>() {

    var searchDbHelper = DBSearchHelper(ctx)
    lateinit var searchDB: SQLiteDatabase

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder{
        //뷰 인플레이트
        var view : View = LayoutInflater.from(ctx).inflate(R.layout.search_history_item, parent, false)
        return Holder(view)
    }

    override fun getItemCount() : Int = searchData.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.closeBtn.setOnClickListener {
            var keyword = searchData[position]

            searchData.removeAt(position)
            searchActivity.deleteKeyword(keyword,searchDbHelper)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, searchData.size)
        }

        holder.container.setOnClickListener {

            Log.e("holder.container.setOnClickListener","insert insert insert!!!!!")

            var keyword = searchData[position]
            searchActivity.setKeyword(keyword)

            searchActivity.performSearch(searchData[position])
            searchActivity.insertKeyword(searchData[position], searchDbHelper)

            searchDB= searchDbHelper.writableDatabase
            searchActivity.insertSearchHistoryData(searchDB)
            searchActivity.setSearchResultView()
        }

        holder.historyKeyword.text = searchData[position]
    }

    inner class Holder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val historyKeyword : TextView = itemView.findViewById(R.id.iv_search_history_item_contents)
        val closeBtn :ImageView = itemView.findViewById(R.id.iv_search_history_item_close)
        val container : RelativeLayout = itemView.findViewById(R.id.rl_search_history_item_container)
    }

}