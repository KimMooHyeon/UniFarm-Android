package com.song2.unifarm

import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.support.v4.widget.DrawerLayout
import android.support.design.widget.NavigationView
import android.support.v4.widget.ImageViewCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuInflater
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv_main_act_now_title.isSelected=true
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        setTitle("")
        navView.setNavigationItemSelectedListener(this)

        iv_main_act_btn_reward.setOnClickListener {
            startActivity<RewordActivity>()
        }

        rl_main_act_comming_act.setOnClickListener {
            startActivity<KotlinCalendar>()
        }

        rl_main_act_major_btn.setOnClickListener {
            startActivity<DetailedActivity>("idxxx" to 1)
        }

        rl_main_act_keyword_btn.setOnClickListener {
            startActivity<DetailedActivity>("idxxx" to 2)
        }

    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    //뷰 연결
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    //클릭
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.menu_search -> startActivity<SearchActivity>()
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_collect_view -> {
                startActivity<CollectViewActivity>()
            }
            R.id.nav_bookmark -> {
                startActivity<BookMarkActivity>()

            }
            R.id.nav_calendar -> {
                startActivity<KotlinCalendar>()

            }
            R.id.nav_notice -> {
                startActivity<SettingActivity>()

            }
            R.id.nav_setting -> {
                startActivity<SettingActivity>()

            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}
