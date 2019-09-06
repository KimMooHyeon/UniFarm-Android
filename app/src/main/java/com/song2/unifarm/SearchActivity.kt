package com.song2.unifarm

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import com.song2.unifarm.Adapter.SearchHistoryAdapter
import com.song2.unifarm.DB.DBSearchHelper
import kotlinx.android.synthetic.main.activity_search.*
import org.jetbrains.anko.ctx
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.toast

class SearchActivity : AppCompatActivity(){
    lateinit var searchData: ArrayList<String>
    lateinit var searchDbHelper: DBSearchHelper
    lateinit var searchDB: SQLiteDatabase
    lateinit var searchHistoryAdapter: SearchHistoryAdapter


    lateinit var cursor: Cursor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        searchDbHelper = DBSearchHelper(ctx)
        searchDB = searchDbHelper.writableDatabase

        //keyboard - searchBtn
        insertSearchHistoryData(searchDB)

        setHistoryRecyclerView()

        et_search_act_search_contents.setOnEditorActionListener({ textView, actionId, keyEvent ->
            var handled = false

            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                var keyword = et_search_act_search_contents.text.toString()
                if (keyword.equals(""))
                    toast("적어도 한 글자 이상을 입력 해 주세요")
                else {
                    performSearch(keyword)
                    insertKeyword(keyword, searchDbHelper)

                    searchDbHelper = DBSearchHelper(ctx)
                    searchDB= searchDbHelper.writableDatabase

                    insertSearchHistoryData(searchDB)
                }
            }
            handled
        })

        // Edittext focus ON
        et_search_act_search_contents.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {

                insertSearchHistoryData(searchDB)
                rv_history_search.visibility = View.VISIBLE

                searchDbHelper = DBSearchHelper(ctx)
                searchDB= searchDbHelper.writableDatabase

                insertSearchHistoryData(searchDB)

                searchEditTextFocusOn()
            }else{
                rv_history_search.visibility = View.GONE
            }
        }
        // edt delete
        iv_search_act_delete_btn.setOnClickListener {
            et_search_act_search_contents.setText("")
            rv_history_search.visibility = View.VISIBLE
        }

    }

    fun setHistoryRecyclerView(){

    }

    fun searchEditTextFocusOn() {

        //검색결과 view gone

        //searchData.add("# 농활")
        //searchData.add("# 감자캐기")

        searchHistoryAdapter = SearchHistoryAdapter(ctx, this ,searchData)
        rv_history_search.adapter = searchHistoryAdapter
        rv_history_search.layoutManager = LinearLayoutManager(ctx)

        rv_history_search.visibility = View.VISIBLE
    }

    fun deleteAllHistoryData(){

        searchDbHelper.deleteAll()
        searchData.clear()

        searchHistoryAdapter = SearchHistoryAdapter(ctx, this ,searchData)
        rv_history_search.adapter = searchHistoryAdapter
        rv_history_search.layoutManager = LinearLayoutManager(ctx)
        rv_history_search.isNestedScrollingEnabled = false

        //검색결과 존재X 뷰
        rv_history_search.visibility = View.GONE
        tv_search_act_no_result.visibility = View.VISIBLE
    }

    fun insertSearchHistoryData(searchDB: SQLiteDatabase) {

        Log.e("insertSearchHistoryData","insert insert insert!!!!!")

        cursor = searchDB.rawQuery("SELECT * FROM SEARCH ORDER BY _id DESC;", null)

        searchData = ArrayList<String>()

        while (cursor.moveToNext()) {
            searchData.add(cursor.getString(1))
            Log.v("searchData", searchData.toString())
        }

        //최근 검색어 없을 경우
        if (cursor.count.equals(0)) {
            //
            tv_search_act_no_result.visibility = View.VISIBLE
            return
        }

        //rv_history_search.visibility = View.GONE

        searchHistoryAdapter = SearchHistoryAdapter(ctx, this,searchData)
        searchHistoryAdapter.notifyDataSetChanged()
        rv_history_search.adapter = searchHistoryAdapter
        rv_history_search.layoutManager = LinearLayoutManager(ctx)
        rv_history_search.isNestedScrollingEnabled = false
    }

    fun insertKeyword(keyword: String, searchDbHelper: DBSearchHelper) {

        Log.e("insertKeyword","insert insert insert!!!!!")
        //이미 존재 할 경우, 이전데이터 지우고 insert
        if (searchDbHelper.search(keyword)) {
            searchDbHelper.delete(keyword)
        }
        searchDbHelper.insert(keyword)

        //insertSearchHistoryData(searchDB)
    }

    fun deleteKeyword(keyword: String, searchDbHelper: DBSearchHelper) {
        searchDbHelper.delete(keyword)
    }

    fun setKeyword(keyword: String){
        et_search_act_search_contents.setText(keyword)
    }


    fun performSearch(keyword: String) {

        //통신
        //getSearchResponse(keyword)

        rv_history_search.visibility = View.GONE

        val imm = ctx!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(et_search_act_search_contents.windowToken, 0)
    }

}
