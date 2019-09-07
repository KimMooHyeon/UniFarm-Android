package com.song2.unifarm

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.song2.unifarm.DB.SharedPreferenceController
import com.song2.unifarm.Data.keywordsList
import com.song2.unifarm.Data.userData
import com.song2.unifarm.Data.userData2
import com.song2.unifarm.Network.ApplicationController
import com.song2.unifarm.Network.NetworkService
import com.song2.unifarm.Network.POST.PostLoginResponse
import com.song2.unifarm.Network.POST.PostSignupResponse
import kotlinx.android.synthetic.main.activity_sign_up2.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUp2Activity : AppCompatActivity() {
    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }
    lateinit var sorting : ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up2)
        var sex = "M"
        cb_signup2_man.setOnClickListener {
            sex="M"
            cb_signup2_girl.isChecked=false
        }
        cb_signup2_girl.setOnClickListener {
            sex="G"
            cb_signup2_man.isChecked=false

        }
        //뒤로가기
        iv_signup2_back.setOnClickListener {
            finish()
        }
        //회원가입 완료버튼
        tv_signup2_finish.setOnClickListener {
           // SignupPost()//회원가입 통신
            startActivity<LoginActivity>()
            finish()

        }
    }
    private fun SignupPost() {
          var jsonObject= userData2(intent.getStringExtra("password")
            ,intent.getStringExtra("email"),intent.getStringExtra("name"),
            intent.getStringExtra("entranceYear"),
            intent.getStringExtra("major"),
            intent.getStringExtra("sex"))

        sorting= ArrayList()
        sorting.add("농활")
        sorting.add("강원도")
        sorting.add("벽화봉사")
        sorting.add("코딩봉사")

        val postSignUpResponse: Call<PostSignupResponse> =
            networkService.postSignupResponse("application/json", jsonObject, keywordsList(sorting))
        postSignUpResponse.enqueue(object : Callback<PostSignupResponse> {
            override fun onFailure(call: Call<PostSignupResponse>, t: Throwable) {
                toast("통신 실패")
            }

            override fun onResponse(call: Call<PostSignupResponse>, response: Response<PostSignupResponse>) {
                if (response.isSuccessful) {
                    val message = response.body()!!.message!!
                        toast(message)
                    startActivity<LoginActivity>()
                    finish()
                }
            }
        })

    }
}
