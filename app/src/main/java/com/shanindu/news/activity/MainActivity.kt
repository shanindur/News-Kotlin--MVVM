package com.shanindu.news.activity


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.shanindu.news.R
import com.shanindu.news.adapter.PageAdapter

import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    var tabLayout: TabLayout? = null
    var viewPager: ViewPager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        viewPager = findViewById<ViewPager>(R.id.viewPager)

        tabLayout!!.addTab(tabLayout!!.newTab().setText("Home"))
        tabLayout!!.addTab(tabLayout!!.newTab().setText("Custom"))
        tabLayout!!.addTab(tabLayout!!.newTab().setText("Profile"))
        tabLayout!!.tabGravity = TabLayout.GRAVITY_FILL

        val adapter = PageAdapter(this, supportFragmentManager, tabLayout!!.tabCount)
        viewPager!!.adapter = adapter

        viewPager!!.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

        tabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager!!.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {

            }
            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })

    }
}
