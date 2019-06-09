package com.example.pocket

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pocket.bean.EventItem
import com.example.pocket.date.DateDialog
import com.google.android.material.tabs.TabLayout
import com.savvi.rangedatepicker.CalendarPickerView
import kotlinx.android.synthetic.main.fragment_remind.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class RemindFragment : Fragment() {
    private var listener: OnFragmentInteractionListener? = null
    private val eventList: ArrayList<EventItem> = arrayListOf()
    private val finishList: ArrayList<EventItem> = arrayListOf()
    private lateinit var adapter: RemindRecycAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_remind, container, false)
    }

    @SuppressLint("RestrictedApi")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if ((activity as MainActivity).isParent) add_remind_button.visibility = View.GONE
        else add_remind_button.visibility = View.VISIBLE
        GlobalScope.launch {
            while (true) {
                activity?.runOnUiThread {
                    time_text.text =
                        SimpleDateFormat("HH:mm", Locale.CHINA).format(System.currentTimeMillis())
                }
                delay(1000)
            }
        }
        remind_tablayout.addTab(remind_tablayout.newTab().setText("待完成"), true)
        remind_tablayout.addTab(remind_tablayout.newTab().setText("已完成"))
        remind_tablayout.addOnTabSelectedListener(object :
            TabLayout.BaseOnTabSelectedListener<TabLayout.Tab> {
            override fun onTabReselected(tab: TabLayout.Tab) {}

            override fun onTabUnselected(tab: TabLayout.Tab) {}

            override fun onTabSelected(tab: TabLayout.Tab) {
                if (tab.position == 0)
                    adapter.eventList = eventList
                else
                    adapter.eventList = finishList
                adapter.notifyDataSetChanged()
            }

        })
        eventList.add(EventItem("记得吃维生素片", "乖乖", "麻麻", System.currentTimeMillis() + 1000000, false))
        eventList.add(EventItem("给宝宝买一套字帖", "乖乖", "麻麻", System.currentTimeMillis() + 2000000, false))
        eventList.add(EventItem("晚上小姨来做客", "乖乖", "麻麻", System.currentTimeMillis() + 30000000, false))
        adapter = RemindRecycAdapter(eventList, (activity as MainActivity).isParent)
        remind_event_recycler.adapter = adapter
        remind_event_recycler.layoutManager = LinearLayoutManager(context)

        remind_date_change.setOnClickListener {
            val dialog = DateDialog(context!!, CalendarPickerView.SelectionMode.SINGLE, true)
            dialog.show()

            dialog.setOnDismissListener {
                val list = dialog.dateList
                if (list.isNotEmpty()) {
                    val date = list.first()
                    val dateFormat = SimpleDateFormat("YYYY年MM月dd日", Locale.CHINA)
                    val time = dateFormat.format(date)
                    remind_date_text.text = time
                }
            }
        }
        add_remind_button.setOnClickListener {
            startActivityForResult(Intent(context, RemindAddActivity::class.java), 1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            1 -> {
                if (resultCode == 1) {
                    eventList.add(
                        EventItem(
                            data?.getStringExtra(RemindAddActivity.SEND_CONTENT) ?: "",
                            "我",
                            data?.getStringExtra(RemindAddActivity.SEND_TO) ?: "",
                            data?.getLongExtra(RemindAddActivity.REMIND_TIME, System.currentTimeMillis() + 100000) ?: 0,
                            false
                        )
                    )
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }

    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        @JvmStatic
        fun newInstance() = RemindFragment()
    }
}
