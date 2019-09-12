package com.song2.unifarm

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_review.*
import org.jetbrains.anko.startActivity
import android.support.v4.app.SupportActivity
import android.support.v4.app.SupportActivity.ExtraData
import android.support.v4.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.support.v4.app.ActivityCompat
import org.jetbrains.anko.toast


class ReviewActivity : AppCompatActivity() {
    override fun onBackPressed() {
   finish()
        this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out)

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)
        tv_review_name.text=intent.getStringExtra("name")
        tv_review_date.text=intent.getStringExtra("date")
        rl_container_touch.setOnClickListener {
            finish()

            this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out)

        }
        iv_review_cancel.setOnClickListener {
            finish()
            this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }
        tv_review_confirm.setOnClickListener {
            startActivity<ThanksForReviewActivity>()
            finish()
            this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }
    }

}
