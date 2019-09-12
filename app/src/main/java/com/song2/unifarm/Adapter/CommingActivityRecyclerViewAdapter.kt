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
import com.song2.unifarm.Data.CollectPopularProgramData
import com.song2.unifarm.Data.CommingActivityData
import com.song2.unifarm.R
import de.hdodenhof.circleimageview.CircleImageView


class CommingActivityRecyclerViewAdapter(var ctx: Context, var dataList: ArrayList<CommingActivityData>) :
    RecyclerView.Adapter<CommingActivityRecyclerViewAdapter.Holder>() {
    override fun onCreateViewHolder(viewgroup: ViewGroup, position: Int): Holder {
        val view: View = LayoutInflater.from(ctx).inflate(com.song2.unifarm.R.layout.rv_comming_activity_item, viewgroup, false)

        return Holder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {

        holder.tv_comming_date.text=dataList[position].startDate.toString()
        holder.tv_comming_programname.text=dataList[position].title.toString()
        if(dataList[position].dday.toString()== "-1"){
            holder.tv_comming_dday.visibility=View.INVISIBLE
        }
        holder.tv_comming_dday.text="D-"+dataList[position].dday.toString()
        holder.tv_comming_act_more_btn.setOnClickListener {
            //더보기 아이템 클릭 (프로그램으로 이동하는거 만들기!)
        }
        if(position==0){
            holder.cv_comming_circle.setImageResource(com.song2.unifarm.R.color.default_selected_day_background_start_color)


        }else if(position ==1){
            holder.cv_comming_circle.setImageResource(com.song2.unifarm.R.color.default_connected_day_text_color)
        }else if(position==2){
            holder.cv_comming_circle.setImageResource(com.song2.unifarm.R.color.default_selection_bar_month_title_text_color)
        }else{
            holder.cv_comming_circle.setImageResource(com.song2.unifarm.R.color.default_month_text_color)
        }
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cv_comming_circle=itemView.findViewById(com.song2.unifarm.R.id.cv_comming_circle) as CircleImageView
        var tv_comming_programname=itemView.findViewById(com.song2.unifarm.R.id.tv_comming_programname) as TextView
        var tv_comming_dday=itemView.findViewById(com.song2.unifarm.R.id.tv_comming_dday) as TextView
        var tv_comming_date = itemView.findViewById(com.song2.unifarm.R.id.tv_comming_date) as TextView
        var tv_comming_act_more_btn = itemView.findViewById(com.song2.unifarm.R.id.tv_comming_act_more_btn) as TextView

    }

}