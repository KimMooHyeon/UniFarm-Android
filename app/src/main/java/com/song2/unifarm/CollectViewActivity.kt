package com.song2.unifarm

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.song2.unifarm.Adapter.CollectViewPopularRecyclerViewAdapter
import com.song2.unifarm.Adapter.RecommendProgramRecyclerViewAdapter
import com.song2.unifarm.Data.CollectPopularProgramData
import com.song2.unifarm.Data.RecommendCollectViewData
import kotlinx.android.synthetic.main.activity_collect_view.*

class CollectViewActivity : AppCompatActivity() {
    lateinit var collectViewPopularRecyclerViewAdapter: CollectViewPopularRecyclerViewAdapter
    var CollectdataList: ArrayList<CollectPopularProgramData> = ArrayList()
    lateinit var recommendProgramRecyclerViewAdapter: RecommendProgramRecyclerViewAdapter
    var RecommenddataList: ArrayList<RecommendCollectViewData> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collect_view)

        iv_collect_view_home.setOnClickListener {
            finish()
        }

        tv_collect_major_standard.setOnClickListener {
            //전공기준 클릭
            cv_collect_major_standard.visibility=View.VISIBLE
            cv_collect_interestingKeword_standard.visibility=View.INVISIBLE
            tv_collect_interestingKeword_standard.setTextColor(Color.parseColor("#cecece"))
            tv_collect_major_standard.setTextColor(Color.parseColor("#333333"))
        }
        tv_collect_interestingKeword_standard.setOnClickListener {
            //관심사클릭
            cv_collect_major_standard.visibility=View.INVISIBLE
            cv_collect_interestingKeword_standard.visibility=View.VISIBLE
            tv_collect_interestingKeword_standard.setTextColor(Color.parseColor("#333333"))
            tv_collect_major_standard.setTextColor(Color.parseColor("#cecece"))
        }

        RecommenddataList.add(RecommendCollectViewData("https://project-youngwoo.s3.ap-northeast-2.amazonaws.com/program_01.jpg","충북 보은 마을재생","#충북","41","50"))
        RecommenddataList.add(RecommendCollectViewData("https://project-youngwoo.s3.ap-northeast-2.amazonaws.com/program_03.jpg","충남 보은 마을재생","#충남","43","50"))
        RecommenddataList.add(RecommendCollectViewData("https://project-youngwoo.s3.ap-northeast-2.amazonaws.com/program_01.jpg","경북 보은 마을재생","#경북","45","50"))
        RecommenddataList.add(RecommendCollectViewData("https://project-youngwoo.s3.ap-northeast-2.amazonaws.com/program_02.jpg","충북 보은 마을재생","#충북","41","50"))
        RecommenddataList.add(RecommendCollectViewData("https://project-youngwoo.s3.ap-northeast-2.amazonaws.com/program_03.jpg","충남 보은 마을재생","#충남","43","50"))
        RecommenddataList.add(RecommendCollectViewData("https://project-youngwoo.s3.ap-northeast-2.amazonaws.com/program_01.jpg","경북 보은 마을재생","#경북","45","50"))
        RecommenddataList.add(RecommendCollectViewData("https://project-youngwoo.s3.ap-northeast-2.amazonaws.com/program_02.jpg","충북 보은 마을재생","#충북","41","50"))
        RecommenddataList.add(RecommendCollectViewData("https://project-youngwoo.s3.ap-northeast-2.amazonaws.com/program_02.jpg","충남 보은 마을재생","#충남","43","50"))
        RecommenddataList.add(RecommendCollectViewData("https://project-youngwoo.s3.ap-northeast-2.amazonaws.com/program_03.jpg","경북 보은 마을재생","#경북","45","50"))
        recommendProgramRecyclerViewAdapter = RecommendProgramRecyclerViewAdapter(this, RecommenddataList)
        rv_recommend_program.adapter = recommendProgramRecyclerViewAdapter


        CollectdataList.add(CollectPopularProgramData("https://project-youngwoo.s3.ap-northeast-2.amazonaws.com/program_01.jpg","인천광역시","공부하기"))
        CollectdataList.add(CollectPopularProgramData("https://project-youngwoo.s3.ap-northeast-2.amazonaws.com/program_02.jpg","부산광역시","놀기"))
        CollectdataList.add(CollectPopularProgramData("https://project-youngwoo.s3.ap-northeast-2.amazonaws.com/program_03.jpg","서울특별시","취업하기"))
        CollectdataList.add(CollectPopularProgramData("https://project-youngwoo.s3.ap-northeast-2.amazonaws.com/program_02.jpg","인천광역시","공부하기"))
        CollectdataList.add(CollectPopularProgramData("https://project-youngwoo.s3.ap-northeast-2.amazonaws.com/program_01.jpg","부산광역시","놀기"))
        CollectdataList.add(CollectPopularProgramData("https://project-youngwoo.s3.ap-northeast-2.amazonaws.com/program_03.jpg","서울특별시","취업하기"))
        CollectdataList.add(CollectPopularProgramData("https://project-youngwoo.s3.ap-northeast-2.amazonaws.com/program_02.jpg","인천광역시","공부하기"))
        CollectdataList.add(CollectPopularProgramData("https://project-youngwoo.s3.ap-northeast-2.amazonaws.com/program_03.jpg","부산광역시","놀기"))
        CollectdataList.add(CollectPopularProgramData("https://project-youngwoo.s3.ap-northeast-2.amazonaws.com/program_02.jpg","서울특별시","취업하기"))
        collectViewPopularRecyclerViewAdapter = CollectViewPopularRecyclerViewAdapter(this, CollectdataList)
        rv_collect_pupular.adapter = collectViewPopularRecyclerViewAdapter
        rv_collect_pupular.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
    }
}
