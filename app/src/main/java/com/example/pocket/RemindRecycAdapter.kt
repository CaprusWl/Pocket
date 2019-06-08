package com.example.pocket

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pocket.bean.EventItem
import java.text.SimpleDateFormat
import java.util.Locale

class RemindRecycAdapter(var eventList: List<EventItem>) : RecyclerView.Adapter<RemindRecycAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.remind_card, parent, false))

    override fun getItemCount() = eventList.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.eventNameText.text = eventList[position].name
        holder.fromText.text = "来自:" + eventList[position].fromID
        val date = SimpleDateFormat("MM-dd", Locale.CHINA).format(eventList[position].time)
        holder.eventTimeText.text = date
        holder.finishImage.visibility =
            if (eventList[position].time > System.currentTimeMillis()) View.VISIBLE else View.GONE
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val eventNameText: TextView = v.findViewById(R.id.event_name_text)
        val fromText: TextView = v.findViewById(R.id.from_text)
        val eventTimeText: TextView = v.findViewById(R.id.event_time_text)
        val finishImage: ImageView = v.findViewById(R.id.finish_icon)
    }
}