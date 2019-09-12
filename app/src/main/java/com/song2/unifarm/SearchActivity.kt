package com.song2.unifarm

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.opengl.Visibility
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import com.song2.unifarm.Adapter.SearchAdapter
import com.song2.unifarm.Adapter.SearchHistoryAdapter
import com.song2.unifarm.DB.DBSearchHelper
import com.song2.unifarm.Data.SearchResult
import com.song2.unifarm.Network.ApplicationController
import com.song2.unifarm.Network.GET.GetSearchResponse
import com.song2.unifarm.Network.GET.ProgramDataN
import com.song2.unifarm.Network.NetworkService
import kotlinx.android.synthetic.main.activity_search.*
import org.jetbrains.anko.ctx
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchActivity : AppCompatActivity() {

    var isSuccesssssss = 0
    lateinit var searchData: ArrayList<String>
    lateinit var searchResultData: ArrayList<SearchResult>

    lateinit var searchDbHelper: DBSearchHelper
    lateinit var searchDB: SQLiteDatabase

    lateinit var searchHistoryAdapter: SearchHistoryAdapter
    lateinit var searchAdapter: SearchAdapter

    lateinit var cursor: Cursor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        searchResultData = ArrayList()

        searchDbHelper = DBSearchHelper(ctx)
        searchDB = searchDbHelper.writableDatabase

        ll_search_result_container.visibility = View.GONE

        //keyboard - searchBtn
        insertSearchHistoryData(searchDB)

        setHistoryRecyclerView()
        //setSearchResult()

        iv_search_act_back_btn.setOnClickListener {
            finish()
        }

        et_search_act_search_contents.setOnEditorActionListener({ textView, actionId, keyEvent ->
            var handled = false

            rl_search_result_non_container.visibility = View.GONE


            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                ll_search_result_container.visibility = View.GONE //검색결과


                var keyword = et_search_act_search_contents.text.toString()
                if (keyword.equals(""))
                    toast("적어도 한 글자 이상을 입력 해 주세요")
                else {
                    performSearch(keyword)
                    insertKeyword(keyword, searchDbHelper)

                    searchDbHelper = DBSearchHelper(ctx)
                    searchDB = searchDbHelper.writableDatabase

                    insertSearchHistoryData(searchDB)

                    ll_search_result_container.visibility = View.VISIBLE //검색결과

                    //토신
                    getSearchResponse(keyword)
                }
            }
            handled
        })

        // Edittext focus ON
        et_search_act_search_contents.setOnFocusChangeListener { view, hasFocus ->

            rl_search_result_non_container.visibility = View.GONE

            if (hasFocus) {
                if(isSuccesssssss == -1){
                    isSuccesssssss = 0
                    rl_search_result_non_container.visibility = View.GONE
                }

                ll_search_result_container.visibility = View.GONE //검색결과

                insertSearchHistoryData(searchDB)
                rv_history_search.visibility = View.VISIBLE

                searchDbHelper = DBSearchHelper(ctx)
                searchDB = searchDbHelper.writableDatabase

                insertSearchHistoryData(searchDB)

                searchEditTextFocusOn()
            } else {
                rv_history_search.visibility = View.GONE
                ll_search_result_container.visibility = View.VISIBLE //검색결과

            }
        }
        // edt delete
        iv_search_act_delete_btn.setOnClickListener {
            et_search_act_search_contents.setText("")
            rv_history_search.visibility = View.VISIBLE
            ll_search_result_container.visibility = View.GONE //검색결과
        }

    }

    fun getSearchResponse(keyword: String) {
        var networkService: NetworkService = ApplicationController.instance.networkService
        var getSearchResponse: Call<GetSearchResponse> = networkService.getSearchResponse("application/json", keyword)
        getSearchResponse.enqueue(object : Callback<GetSearchResponse> {
            override fun onResponse(call: Call<GetSearchResponse>?, response: Response<GetSearchResponse>?) {
                Log.v("TAG", "검색 - 서버 통신 연결")
                if (response!!.isSuccessful) {
                    var temp: ArrayList<ProgramDataN> = response.body()!!.data
                    Log.e("검색 성공!", temp.toString())

                    var str: String = ""
                    if (temp != null) {

                        lateinit var searchResult: SearchResult
                        searchResultData.clear()

                        for (i in temp.indices) {
/*                            //Log.e("f로그!!! keywordProgram:",temp[i].keywordProgram[0].toString())
                            Log.e("f로그!!! keywordProgram:",temp[i].keywordProgram[1].toString())
                            Log.e("f로그!!! keywordProgram:",temp[i].keywordProgram[2].toString())
                            Log.e("f로그!!! keywordProgram:",temp[i].keywordProgram[3].toString())*/
                            //Log.e("f로그!!! keywordProgram:",temp[i].toString())
                            //Log.e("f로그!!! keywordProgram:",temp[i].toString())

                            str = "#공대 #농협 #농활 #주말 #봉사"
                            //str = temp[i].keywordPrograms[0].info + " " + temp[i].keywordPrograms[1].info + " " + temp[i].keywordPrograms[2].info + " " + temp[i].keywordPrograms[3].info + " " + temp[i].keywordPrograms[4].info
                            searchResult = SearchResult(temp[i].program.programIdx,temp[i].program.thumbnail, temp[i].program.title, str, false)
                            searchResultData.add(searchResult)
                        }

                        searchAdapter = SearchAdapter(ctx, searchResultData)
                        searchAdapter .notifyDataSetChanged()
                        rv_search_act_result.adapter = searchAdapter
                        rv_search_act_result.layoutManager = LinearLayoutManager(ctx)

                    } else {
                        rl_search_result_non_container.visibility = View.VISIBLE
                        ll_search_result_container.visibility = View.GONE // 중요!
                    }
                } else {
                    Log.v("TAG", " 검색 실패")

                    isSuccesssssss = -1
                    rl_search_result_non_container.visibility = View.VISIBLE

                }
            }

            override fun onFailure(call: Call<GetSearchResponse>?, t: Throwable?) {
                Log.v("TAG", "통신 실패 = " + t.toString())

                isSuccesssssss = -1
                rl_search_result_non_container.visibility = View.VISIBLE

            }
        })

    }

    fun setHistoryRecyclerView() {

    }

    fun searchEditTextFocusOn() {

        //검색결과 view gone

        //searchData.add("# 농활")
        //searchData.add("# 감자캐기")

        searchHistoryAdapter = SearchHistoryAdapter(ctx, this, searchData)
        rv_history_search.adapter = searchHistoryAdapter
        rv_history_search.layoutManager = LinearLayoutManager(ctx)

        rv_history_search.visibility = View.VISIBLE
    }

    fun deleteAllHistoryData() {

        searchDbHelper.deleteAll()
        searchData.clear()

        searchHistoryAdapter = SearchHistoryAdapter(ctx, this, searchData)
        rv_history_search.adapter = searchHistoryAdapter
        rv_history_search.layoutManager = LinearLayoutManager(ctx)
        rv_history_search.isNestedScrollingEnabled = false

        //검색결과 존재X 뷰
        rv_history_search.visibility = View.GONE
    }

    fun insertSearchHistoryData(searchDB: SQLiteDatabase) {

        Log.e("insertSearchHistoryData", "insert insert insert!!!!!")

        cursor = searchDB.rawQuery("SELECT * FROM SEARCH ORDER BY _id DESC;", null)

        searchData = ArrayList<String>()

        while (cursor.moveToNext()) {
            searchData.add(cursor.getString(1))
            Log.v("searchData", searchData.toString())
        }

        //최근 검색어 없을 경우
        if (cursor.count.equals(0)) {

            return
        }

        //rv_history_search.visibility = View.GONE

        searchHistoryAdapter = SearchHistoryAdapter(ctx, this, searchData)
        searchHistoryAdapter.notifyDataSetChanged()
        rv_history_search.adapter = searchHistoryAdapter
        rv_history_search.layoutManager = LinearLayoutManager(ctx)
        rv_history_search.isNestedScrollingEnabled = false
    }

    fun insertKeyword(keyword: String, searchDbHelper: DBSearchHelper) {

        Log.e("insertKeyword", "insert insert insert!!!!!")
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

    fun setKeyword(keyword: String) {
        et_search_act_search_contents.setText(keyword)
    }


    fun performSearch(keyword: String) {

        //통신
        //getSearchResponse(keyword)

        rv_history_search.visibility = View.GONE

        val imm = ctx!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(et_search_act_search_contents.windowToken, 0)
    }

    fun setSearchResult() {

        var searchResult = SearchResult(
            1333,
            "https://t1.daumcdn.net/cfile/tistory/2442394558BBBD1934",
            "강원도 농촌 체험",
            "#후기 #농혐 #감자캐기 #체험",
            false
        )

        var searchResult1 = SearchResult(
            1222,
            "https://t1.daumcdn.net/cfile/tistory/2442394558BBBD1934",
            "전공 체험(컴퓨터공학과)",
            "#코딩 #교육 #초등학생 #공학",
            true
        )

        searchResultData.add(searchResult)
        searchResultData.add(searchResult1)

        searchAdapter = SearchAdapter(ctx, searchResultData)
        rv_search_act_result.adapter = searchAdapter
        rv_search_act_result.layoutManager = LinearLayoutManager(ctx)
    }

    fun setSearchResultView() {
        ll_search_result_container.visibility = View.VISIBLE //검색결과

    }


}
