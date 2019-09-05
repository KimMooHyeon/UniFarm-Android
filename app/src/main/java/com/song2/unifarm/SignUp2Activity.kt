package com.song2.unifarm

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_sign_up2.*
import org.jetbrains.anko.startActivity

class SignUp2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up2)

        //뒤로가기
        iv_signup2_back.setOnClickListener {
            finish()
        }
        //회원가입 완료버튼
        tv_signup2_finish.setOnClickListener {
            startActivity<LoginActivity>()//회원가입 통신
            finish()
        }
    }
}
