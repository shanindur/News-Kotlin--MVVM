package com.shanindu.news.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.shanindu.news.fragment.CustomFragment
import com.shanindu.news.fragment.HomeFragment
import com.shanindu.news.fragment.ProfileFragment


class PageAdapter(
    private val myContext: Context,
    fm: FragmentManager,
    internal var totalTabs: Int
) : FragmentPagerAdapter(fm) {

    // this is for fragment tabs
    override fun getItem(position: Int): Fragment? {
        return when (position) {
            0 -> {
                //  val homeFragment: HomeFragment = HomeFragment()
                HomeFragment()
            }
            1 -> {
                CustomFragment()
            }
            2 -> {
                // val movieFragment = MovieFragment()
                ProfileFragment()
            }
            else -> null
        }
    }

    // this counts total number of tabs
    override fun getCount(): Int {
        return totalTabs
    }

}