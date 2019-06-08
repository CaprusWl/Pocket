package com.example.pocket.memo


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.pocket.R
import com.example.pocket.date.DateDialog
import com.google.android.material.tabs.TabLayout
import java.text.SimpleDateFormat
import java.util.*


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
                val dateFormat = SimpleDateFormat("MM月dd日", Locale.CHINA)
                var timeRange = dateFormat.format(firstDate)
                if (list.size > 1) {
                    timeRange += "——"
                    timeRange += dateFormat.format(lastDate)
                }
                if (list.isNotEmpty()) {
                    timeRangeText.text = timeRange
                }
            }
        }

    }



}
