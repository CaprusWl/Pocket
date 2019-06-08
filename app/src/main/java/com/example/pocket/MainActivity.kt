package com.example.pocket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import androidx.fragment.app.FragmentManager
import com.example.pocket.memo.MemoFragment

class MainActivity : AppCompatActivity() {

    private val fragmentList : ArrayList<Fragment> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pager.adapter = MyFragmentPagerAdapter(supportFragmentManager, fragmentList)

        pager.currentItem = 0

    }
}
