package com.example.pocket.memo


import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupWindow
import android.widget.TextView
import android.widget.Toast

import com.example.pocket.R
import com.example.pocket.date.DateDialog
import com.google.android.material.tabs.TabLayout
import com.savvi.rangedatepicker.CalendarPickerView
import com.savvi.rangedatepicker.SubTitle
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


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
        val timeRangeText = view.findViewById<TextView>(R.id.time_range)
        for (i in titles.indices) {
            val tabLayout = view.findViewById<TabLayout>(R.id.memo_tabLayout)
            val newTab = tabLayout.newTab()
            newTab.text = titles[i]
            tabLayout.addTab(newTab)
            tabLayout.isTabIndicatorFullWidth = false
        }
        val changeDate = view.findViewById<ImageView>(R.id.change_date)

        changeDate.setOnClickListener {
            val dialog = DateDialog(context!!)
            dialog.show()
            dialog.setOnDismissListener {
                val list = dialog.dateList
                val firstDate = list.first()
                val lastDate = list.last()
                var timeRange = firstDate.toString()
                if (list.size > 1) {
                    timeRange += ("——$lastDate")
                }
                if (list.isNotEmpty()) {
                    timeRangeText.text = timeRange
                }
            }
        }

    }



}
