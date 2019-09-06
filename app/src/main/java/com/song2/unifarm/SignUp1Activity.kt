package com.song2.unifarm

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.song2.unifarm.DB.SharedPreferenceController
import com.song2.unifarm.Network.ApplicationController
import com.song2.unifarm.Network.GET.getCheckEmailResponse
import com.song2.unifarm.Network.NetworkService
import com.song2.unifarm.Network.POST.PostLoginResponse
import kotlinx.android.synthetic.main.activity_sign_up1.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUp1Activity : AppCompatActivity() {
    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up1)
        //뒤로가기
        iv_signup1_back.setOnClickListener {
            finish()
        }
        iv_signup1_next.setOnClickListener {
            startActivity<SignUp2Activity>("password" to edt_signup1_pw.text.toString(),
                "email" to edt_signup1_id.text.toString(),
                "name" to edt_signup1_name.text.toString(),
                "entranceYear" to edt_signup1_entranceYear.text.toString())
            finish()
        }
        tv_signup_overlap.setOnClickListener {
            if(edt_signup1_id.text.isNotEmpty()){
                checkEmail()
            }else{
                toast("이메일을 입력해주세요")
            }

        }
    }
    private fun checkEmail() {

        val getCheckEmailResponse: Call<getCheckEmailResponse> =
            networkService.getCheckEmailResponse("application/json", edt_signup1_id.text.toString())
        getCheckEmailResponse.enqueue(object : Callback<getCheckEmailResponse> {
            override fun onFailure(call: Call<getCheckEmailResponse>, t: Throwable) {
                toast("통신 실패")
            }

            override fun onResponse(call: Call<getCheckEmailResponse>, response: Response<getCheckEmailResponse>) {
                if (response.isSuccessful) {
                    val message = response.body()!!.message!!
                    if (response.body()!!.status == 200 ) {
                        toast(message)
                        iv_signup1_next.visibility= View.VISIBLE
                    }else {
                        iv_signup1_next.visibility= View.INVISIBLE
                        toast(message)
                    }
                }
            }
        })

    }
}
