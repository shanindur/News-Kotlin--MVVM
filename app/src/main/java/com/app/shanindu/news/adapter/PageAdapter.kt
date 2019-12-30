package com.app.shanindu.news.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.app.shanindu.news.ui.CustomFragment
import com.app.shanindu.news.ui.HomeFragment
import com.app.shanindu.news.ui.ProfileFragment

class PageAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                HomeFragment()
            }
            1 -> CustomFragment()
            else -> {
                return ProfileFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Headline"
            1 -> "Custom News"
            else -> {
                return "Profile"
            }
        }
    }

}