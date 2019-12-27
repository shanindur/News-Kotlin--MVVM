package com.shanindu.news.util

import android.content.Context
import android.net.ConnectivityManager


class InternetObserver private constructor(context: Context?) {
    private val connectivityManager: ConnectivityManager

    init {
        connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    companion object {

        private var instance: InternetObserver? = null

        @Synchronized
        fun isConnectedToInternet(context: Context?): Boolean {
            if (instance == null) {
                instance = InternetObserver(context)
            }
            val networkInfo = instance!!.connectivityManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnected
        }
    }
}
