package com.song2.unifarm

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.song2.unifarm.Network.ApplicationController
import com.song2.unifarm.Network.NetworkService
import com.song2.unifarm.Network.POST.PostProgram
import com.song2.unifarm.Network.POST.PostResponse
import kotlinx.android.synthetic.main.activity_complete.*
import org.jetbrains.anko.startActivity
import retrofit2.Call
import retrofit2.Response

class CompleteActivity : AppCompatActivity() {

    lateinit var postProgram :PostProgram
    lateinit var networkService: NetworkService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complete)


        rl_complete_act_togoCalendar.setOnClickListener {
            startActivity<KotlinCalendar>()
            finish()
        }

    }


}
