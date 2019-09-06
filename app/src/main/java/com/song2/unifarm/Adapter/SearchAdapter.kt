package com.song2.unifarm.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.song2.unifarm.Data.SearchResult
import com.song2.unifarm.R

class SearchAdapter(val ctx : Context, val searchData : ArrayList<SearchResult>) : RecyclerView.Adapter<SearchAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder{

        //뷰 인플레이트
        var view : View = LayoutInflater.from(ctx).inflate(R.layout.search_result_item, parent, false)
        return Holder(view)
    }

    override fun getItemCount() : Int = searchData.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.container.setOnClickListener {
        }

        Glide.with(ctx).load(searchData[position].thumb).into(holder.thumb)
        holder.title.text = searchData[position].title
        holder.hashTag.text = searchData[position].hashtag

    }

    inner class Holder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var container : RelativeLayout = itemView.findViewById(R.id.rl_search_result_container)
        var thumb : ImageView = itemView.findViewById(R.id.iv_search_result_item_thumb_img)
        var title :TextView = itemView.findViewById(R.id.tv_search_result_act_title)
        var hashTag : TextView = itemView.findViewById(R.id.tv_search_result_act_title)
    }

}