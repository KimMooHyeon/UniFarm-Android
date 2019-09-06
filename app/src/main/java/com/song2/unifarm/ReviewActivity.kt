package com.song2.unifarm

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_review.*
import org.jetbrains.anko.startActivity

class ReviewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)
        rl_container_touch.setOnClickListener {
            finish()
        }
        iv_review_cancel.setOnClickListener {
            finish()
        }
        tv_review_confirm.setOnClickListener {
            startActivity<ThanksForReviewActivity>()
            finish()
        }
    }
}
