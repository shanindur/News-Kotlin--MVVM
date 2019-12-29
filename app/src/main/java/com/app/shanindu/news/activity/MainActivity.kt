package com.app.shanindu.news.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.app.shanindu.news.adapter.PageAdapter
import com.app.shanindu.news.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setTitle("News")

        val fragmentAdapter = PageAdapter(supportFragmentManager)
        viewpager_main.adapter = fragmentAdapter

        tabs_main.setupWithViewPager(viewpager_main)
    }
}
