package com.song2.unifarm

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        iv_login_logo.setOnClickListener{
            startActivity<MainActivity>()
        }

        //회원가입
        iv_login_signUp.setOnClickListener {
            startActivity<SignUp1Activity>()
        }
    }
}
