package com.song2.unifarm

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_reword.*
import org.jetbrains.anko.startActivity

class RewordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reword)

        iv_reword_view_home.setOnClickListener {
            finish()
        }
    }
}
