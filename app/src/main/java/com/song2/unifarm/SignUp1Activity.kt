package com.song2.unifarm

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_sign_up1.*
import org.jetbrains.anko.startActivity

class SignUp1Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up1)
        //뒤로가기
        iv_signup1_back.setOnClickListener {
            finish()
        }
        iv_signup1_next.setOnClickListener {
            startActivity<SignUp2Activity>()
        }
    }
}
