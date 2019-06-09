package com.example.pocket

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pocket.bean.MemoryItem
import com.github.vipulasri.timelineview.TimelineView

class MemoryAdapter(var eventList: List<MemoryItem>, var context: Context) : RecyclerView.Adapter<MemoryAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = eventList[position]
        holder.contentNumText.text = "共${item.photoNum}张照片, ${item.videoNum}段视频"
        holder.fromText.text = "来自: ${item.fromID}"
        holder.memoryDateText.text = "${item.date.month}月${item.date.day}日"
        holder.titleText.text = item.title
        holder.itemView.setOnClickListener {
            val intent = Intent(context, ContentActivity::class.java)
            context.startActivity(intent)
        }
    }

    class ViewHolder(v: View, viewType: Int) : RecyclerView.ViewHolder(v) {
        val memoryDateText: TextView = v.findViewById(R.id.txt_date)
        val fromText: TextView = v.findViewById(R.id.txt_from_whom)
        val titleText: TextView = v.findViewById(R.id.txt_title)
        val contentNumText: TextView = v.findViewById(R.id.txt_content_num)
        val mTimelineView: TimelineView = v.findViewById(R.id.timeline)
        init {
            mTimelineView.initLine(viewType)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_memory, parent, false), viewType)

    override fun getItemCount() = eventList.size

    override fun getItemViewType(position: Int): Int {
        return TimelineView.getTimeLineViewType(position, itemCount)
    }
}