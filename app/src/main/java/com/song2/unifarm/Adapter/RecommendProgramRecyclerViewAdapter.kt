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
import com.song2.unifarm.Data.RecommendCollectViewData
import com.song2.unifarm.DetailedActivity
import com.song2.unifarm.R
import org.jetbrains.anko.startActivity

class RecommendProgramRecyclerViewAdapter(var ctx: Context, var dataList: ArrayList<RecommendCollectViewData>) :
    RecyclerView.Adapter<RecommendProgramRecyclerViewAdapter.Holder>() {
    override fun onCreateViewHolder(viewgroup: ViewGroup, position: Int): Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_recommend_program_item, viewgroup, false)

        return Holder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {

        holder.rl_recommend_program_allview.setOnClickListener {
            ctx.startActivity<DetailedActivity>("idxxx" to dataList[position].idxxx)
        }

        holder.rv_tv_program_name.text=dataList[position].program_name
        holder.rv_iv_program_hashtag.text=dataList[position].hashTag
        holder.rv_iv_program_nownumber.text=dataList[position].now_number
        holder.rv_iv_program_limitnumber.text=dataList[position].limit_number
        Glide.with(ctx)
            .load(dataList[position].recomend_program_img)
            .into(holder.rv_iv_program_img)
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var rl_recommend_program_allview = itemView.findViewById(R.id.rl_recommend_program_allview) as RelativeLayout
        var rv_iv_program_img=itemView.findViewById(R.id.rv_iv_program_img) as ImageView
        var rv_tv_program_name=itemView.findViewById(R.id.rv_tv_program_name) as TextView
        var rv_iv_program_hashtag=itemView.findViewById(R.id.rv_iv_program_hashtag) as TextView
        var rv_iv_program_nownumber=itemView.findViewById(R.id.rv_iv_program_nownumber) as TextView
        var rv_iv_program_limitnumber=itemView.findViewById(R.id.rv_iv_program_limitnumber) as TextView
    }

}