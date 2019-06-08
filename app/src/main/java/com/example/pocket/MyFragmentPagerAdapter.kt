package com.example.pocket

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


class MyFragmentPagerAdapter(fm: FragmentManager?, private var list: List<Fragment>)
    : FragmentPagerAdapter(fm) {

    override fun getItem(p: Int): Fragment {
        return list[p]
    }

    override fun getCount(): Int {
        return list.size
    }
}