package com.example.pocket

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class MyFragmentPagerAdapter(fm: FragmentManager?, private var list: List<Fragment>)
    : FragmentPagerAdapter(fm) {

    override fun getItem(p: Int): Fragment {
        return list[p]
    }

    override fun getCount(): Int {
        return list.size
    }
}