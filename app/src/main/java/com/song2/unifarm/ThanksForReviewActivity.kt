package com.song2.unifarm

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import org.jetbrains.anko.startActivity

class ThanksForReviewActivity : AppCompatActivity() {
    override fun onBackPressed() {
        finish()
        this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out)

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thanks_for_review)
        Handler().postDelayed(Runnable {
         finish()
            this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }, 1500)//
    }
}
