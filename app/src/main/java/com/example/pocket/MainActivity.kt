package com.example.pocket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val fragmentList : ArrayList<Fragment> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragmentList.add(TestFragment.newInstance(1))
        fragmentList.add(TestFragment.newInstance(2))
        fragmentList.add(TestFragment.newInstance(3))

        pager.adapter = MyFragmentPagerAdapter(supportFragmentManager, fragmentList)

        pager.currentItem = 2
    }
}
