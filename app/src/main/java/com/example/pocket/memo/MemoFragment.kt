package com.example.pocket.memo


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pocket.MemoryAdapter
import com.example.pocket.MemoryAddActivity
import com.example.pocket.R
import com.example.pocket.bean.MemoryItem
import com.example.pocket.data.Date
import com.example.pocket.date.DateDialog
import com.google.android.material.tabs.TabLayout
import com.savvi.rangedatepicker.CalendarPickerView
import kotlinx.android.synthetic.main.fragment_memo.*
import kotlinx.android.synthetic.main.fragment_memo.view.*
import java.text.SimpleDateFormat
import java.util.*

class MemoFragment : Fragment() {

    private val titles = arrayOf("全部", "妈妈", "我的")
    private var num: Int = 0
    private val memoryList = arrayListOf<MemoryItem>()
    private lateinit var adapter: MemoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_memo, container, false)
        init(view)
        view.memo_tabLayout.addOnTabSelectedListener(object :
            TabLayout.BaseOnTabSelectedListener<TabLayout.Tab> {
            override fun onTabReselected(tab: TabLayout.Tab) {}

            override fun onTabUnselected(tab: TabLayout.Tab) {}

            override fun onTabSelected(tab: TabLayout.Tab) {
                if (tab.position == 0)
                    adapter.eventList = memoryList
                else
                    adapter.eventList = arrayListOf()
                adapter.notifyDataSetChanged()
            }

        })

        view.btn_add_memory.setOnClickListener {
            val intent = Intent(activity, MemoryAddActivity::class.java)
            startActivity(intent)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        memoryList.add(MemoryItem(Date()))
        memoryList.add(MemoryItem(Date()))
        memoryList.add(MemoryItem(Date()))
        adapter = MemoryAdapter(memoryList)
        memory_recycler.adapter = adapter
        memory_recycler.layoutManager = LinearLayoutManager(context)
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
            val dialog = DateDialog(context!!, CalendarPickerView.SelectionMode.RANGE, true)
            dialog.show()
            dialog.setOnDismissListener {
                val list = dialog.dateList

                if (list.isNotEmpty()) {
                    val firstDate = list.first()
                    val lastDate = list.last()
                    val dateFormat = SimpleDateFormat("MM月dd日", Locale.CHINA)
                    var timeRange = dateFormat.format(firstDate)
                    if (list.size > 1) {
                        timeRange += "——"
                        timeRange += dateFormat.format(lastDate)
                    }
                    timeRangeText.text = timeRange
                }

            }
        }

    }


}
