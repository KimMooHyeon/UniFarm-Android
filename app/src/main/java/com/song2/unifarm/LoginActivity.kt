package com.song2.unifarm

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.song2.unifarm.DB.SharedPreferenceController
import com.song2.unifarm.Network.ApplicationController
import com.song2.unifarm.Network.NetworkService
import com.song2.unifarm.Network.POST.PostLoginResponse
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        postLoginResponse()

        iv_login_logo.setOnClickListener{
            startActivity<MainActivity>()
        }
        tv_login_login.setOnClickListener {
            SigninPost() // 로그인 통신
        }
        //회원가입
        iv_login_signUp.setOnClickListener {
            startActivity<SignUp1Activity>()
        }

    }

    fun postLoginResponse(){

        val jsonObject = JSONObject()
        jsonObject.put("email", "ywooo21@gmail.com")
        jsonObject.put("password", "unifarmpassword")

        val gsonObject: JsonObject = JsonParser().parse(jsonObject.toString()) as JsonObject
        val postLogInResponse = networkService.postLoginResponse("application/json", gsonObject)

        postLogInResponse.enqueue(object : Callback<PostLoginResponse> {

            override fun onFailure(call: Call<PostLoginResponse>, t: Throwable) {
                Log.e("Login fail", t.toString())
            }

            override fun onResponse(call: Call<PostLoginResponse>, response: Response<PostLoginResponse>) {
                Log.v("LoginActivity", "확인2")
                if (response.isSuccessful) {
                    Log.v("LoginActivity", "확인3 message : "+response.body()!!.message)

                } else {
                    Log.v("LoginActivity", "확인5")
                }
            }
        })

    }
    private fun SigninPost() {
        var jsonObject = JSONObject()
        jsonObject.put("email", "ywooo21@gmail.com")
        jsonObject.put("password", "unifarmpassword")
        val gsonObject = JsonParser().parse(jsonObject.toString()) as JsonObject
        val postLoginResponse: Call<PostLoginResponse> =
            networkService.postLoginResponse("application/json", gsonObject)
        postLoginResponse.enqueue(object : Callback<PostLoginResponse> {
            override fun onFailure(call: Call<PostLoginResponse>, t: Throwable) {
                toast("통신 실패")
            }

            override fun onResponse(call: Call<PostLoginResponse>, response: Response<PostLoginResponse>) {
                if (response.isSuccessful) {
                    val message = response.body()!!.message!!
                    if (message == "로그인 성공") {
                        // 토큰 저장
                        SharedPreferenceController.clearAccessToken(this@LoginActivity)
                        SharedPreferenceController.setAccessToken(
                            applicationContext,
                            response.body()!!.data.toString()
                        )
                        startActivity<MainActivity>()
                        finish()
                    }else {
                        toast(message)
                    }
                }
            }
        })

    }
}
