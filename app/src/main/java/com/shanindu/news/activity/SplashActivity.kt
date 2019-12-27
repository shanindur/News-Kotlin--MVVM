package com.shanindu.news.activity

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import com.shanindu.news.R

class SplashActivity : AppCompatActivity() {
    private val SPLASH_TIME_OUT: Long = 2500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()

        val txtVersion = findViewById<TextView>(R.id.txt_version)

        val manager = this.packageManager
        val info = manager.getPackageInfo(this.packageName, PackageManager.GET_ACTIVITIES)

        txtVersion.setText("Version " + info.versionName)


        Handler().postDelayed(Runnable {

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()

        }, SPLASH_TIME_OUT)
    }
}
