package com.song2.unifarm

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.song2.unifarm.Adapter.BookMarkRecyclerViewAdapter
import com.song2.unifarm.Adapter.CommingActivityRecyclerViewAdapter
import com.song2.unifarm.Adapter.EndActivityRecyclerViewAdapter
import com.song2.unifarm.Data.BookMarkData
import com.song2.unifarm.Data.CommingActivityData
import com.song2.unifarm.Data.EndActivityData
import kotlinx.android.synthetic.main.activity_book_mark.*
import kotlinx.android.synthetic.main.activity_calendar.*

class BookMarkActivity : AppCompatActivity() {
    lateinit var bookMarkRecyclerViewAdapter: BookMarkRecyclerViewAdapter
    var arrayList: ArrayList<BookMarkData> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_mark)
        arrayList.add(BookMarkData("https://project-youngwoo.s3.ap-northeast-2.amazonaws.com/program_01.jpg", "충북 보은 마을 재생 프로젝트", "#충북 #보은 #재생"))
        arrayList.add(BookMarkData("https://project-youngwoo.s3.ap-northeast-2.amazonaws.com/program_02.jpg", "익산시 농기계 수리 농활", "#익산 #농기계 #수리"))
        arrayList.add(BookMarkData("https://project-youngwoo.s3.ap-northeast-2.amazonaws.com/program_03.jpg", "고성 초등학교 SW 멘토링", "#고성 #초등학교 # 멘토링"))
        arrayList.add(BookMarkData("https://project-youngwoo.s3.ap-northeast-2.amazonaws.com/program_01.jpg", "충북 보은 마을 재생 프로젝트", "#충북 #보은 #재생"))
        arrayList.add(BookMarkData("https://project-youngwoo.s3.ap-northeast-2.amazonaws.com/program_02.jpg", "익산시 농기계 수리 농활", "#익산 #농기계 #수리"))
        arrayList.add(BookMarkData("https://project-youngwoo.s3.ap-northeast-2.amazonaws.com/program_03.jpg", "고성 초등학교 SW 멘토링", "#고성 #초등학교 # 멘토링"))
        arrayList.add(BookMarkData("https://project-youngwoo.s3.ap-northeast-2.amazonaws.com/program_01.jpg", "충북 보은 마을 재생 프로젝트", "#충북 #보은 #재생"))
        arrayList.add(BookMarkData("https://project-youngwoo.s3.ap-northeast-2.amazonaws.com/program_02.jpg", "익산시 농기계 수리 농활", "#익산 #농기계 #수리"))
        arrayList.add(BookMarkData("https://project-youngwoo.s3.ap-northeast-2.amazonaws.com/program_03.jpg", "고성 초등학교 SW 멘토링", "#고성 #초등학교 # 멘토링"))
        bookMarkRecyclerViewAdapter = BookMarkRecyclerViewAdapter(this, arrayList)
        rl_bookmark.adapter = bookMarkRecyclerViewAdapter

    }
}
