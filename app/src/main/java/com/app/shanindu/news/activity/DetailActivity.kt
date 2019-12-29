package com.app.shanindu.news.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.app.shanindu.news.R
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity() {

    private var imgNews: ImageView? = null
    private var txtDescription: TextView? = null
    private var txtContent: TextView? = null
    private var txtAuthor: TextView? = null
    private var txtPublishAt: TextView? = null


    private var url: String? = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        //actionbar
        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = ""
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)

        var intent = intent
        val author = intent.getStringExtra("author")
        url = intent.getStringExtra("url")
        val publishat = intent.getStringExtra("publishedAt")
        val description = intent.getStringExtra("description")
        val content = intent.getStringExtra("content")
        val urlToImage = intent.getStringExtra("urlToImage")

        initComponent()

        Picasso.get().load(urlToImage).placeholder(R.drawable.placeholder).into(imgNews)
        txtDescription?.setText(description)
        txtContent?.setText(content)
        txtAuthor?.setText("By " + author)
        txtPublishAt?.setText("Published " + publishat)


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu to use in the action bar
        val inflater = menuInflater
        inflater.inflate(R.menu.toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle presses on the action bar menu items
        when (item.itemId) {
            R.id.action_open -> {
                val openURL = Intent(Intent.ACTION_VIEW)
                openURL.data = Uri.parse(url)
                startActivity(openURL)
                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }

    private fun initComponent() {

        imgNews = findViewById<ImageView>(R.id.img_news_detail)
        txtDescription = findViewById<TextView>(R.id.txt_description)
        txtAuthor = findViewById<TextView>(R.id.txt_author)
        txtContent = findViewById<TextView>(R.id.txt_content)
        txtPublishAt = findViewById<TextView>(R.id.txt_publish_at)


    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
