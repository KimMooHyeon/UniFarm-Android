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
import com.song2.unifarm.Data.BookMarkData
import com.song2.unifarm.Data.CollectPopularProgramData
import com.song2.unifarm.R

class BookMarkRecyclerViewAdapter(var ctx: Context, var dataList: ArrayList<BookMarkData>) :
    RecyclerView.Adapter<BookMarkRecyclerViewAdapter.Holder>() {
    override fun onCreateViewHolder(viewgroup: ViewGroup, position: Int): Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_bookmark_item, viewgroup, false)

        return Holder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {

        holder.rv_tv_program_name.text=dataList[position].program_name.toString()
        holder.rv_iv_program_hashtag.text=dataList[position].hashtag.toString()

        Glide.with(ctx)
            .load(dataList[position].program_img)
            .into(holder.rv_iv_program_img)
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var rv_tv_program_name=itemView.findViewById(R.id.rv_tv_program_name) as TextView
        var rv_iv_program_hashtag=itemView.findViewById(R.id.rv_iv_program_hashtag) as TextView
        var rv_iv_program_img=itemView.findViewById(R.id.rv_iv_program_img) as ImageView
    }

}