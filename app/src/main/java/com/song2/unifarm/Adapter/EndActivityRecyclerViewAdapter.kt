package com.song2.unifarm.Adapter

import android.content.Context
import com.song2.unifarm.Data.EndActivityData
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.song2.unifarm.Data.CommingActivityData
import com.song2.unifarm.R
import com.song2.unifarm.ReviewActivity
import org.jetbrains.anko.startActivity

class EndActivityRecyclerViewAdapter(var ctx: Context, var dataList: ArrayList<EndActivityData>) :
    RecyclerView.Adapter<EndActivityRecyclerViewAdapter.Holder>() {
    override fun onCreateViewHolder(viewgroup: ViewGroup, position: Int): Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_end_activity_item, viewgroup, false)

        return Holder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.tv_end_date.text=dataList[position].startDate.toString()
        holder.tv_end_programname.text=dataList[position].title.toString()
        if(dataList[position].dday.toString()=="-1"){
            holder.tv_end_dday.visibility=View.INVISIBLE
        }
        holder.tv_end_dday.text=dataList[position].dday.toString()
        holder.tv_end_act_more_btn.setOnClickListener {
           ctx.startActivity<ReviewActivity>() //후기 쓰러가기
        }

    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var tv_end_programname=itemView.findViewById(R.id.tv_end_programname) as TextView
        var tv_end_dday=itemView.findViewById(R.id.tv_end_dday) as TextView
        var tv_end_date = itemView.findViewById(R.id.tv_end_date) as TextView
        var tv_end_act_more_btn = itemView.findViewById(R.id.tv_end_act_more_btn) as TextView

    }

}