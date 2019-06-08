package com.example.pocket.memo


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.pocket.R
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_memo.*

class MemoFragment : Fragment() {

    private val titles = arrayOf("全部", "妈妈", "我的")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_memo, container, false)
        init(view)
        return view
    }

    private fun init(view: View) {
        for (i in titles.indices) {
            val tabLayout = view.findViewById<TabLayout>(R.id.memo_tabLayout)
            val newTab = tabLayout.newTab()
            newTab.text = titles[i]
            tabLayout.addTab(newTab)
            tabLayout.isTabIndicatorFullWidth = false
        }
    }


}
