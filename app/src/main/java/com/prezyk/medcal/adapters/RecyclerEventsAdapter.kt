package com.prezyk.medcal.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.prezyk.medcal.R
import com.prezyk.medcal.model.Event

class RecyclerEventsAdapter(private val events: ArrayList<Event>) :  RecyclerView.Adapter<RecyclerEventsAdapter.EventHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerEventsAdapter.EventHolder {
        val row = LayoutInflater.from(parent?.context).inflate(R.layout.display_events_recyclerview_item, parent, false)
        return RecyclerEventsAdapter.EventHolder(row)
    }

    override fun getItemCount(): Int {
        return this.events.size
    }

    override fun onBindViewHolder(holder: RecyclerEventsAdapter.EventHolder, position: Int) {
        holder?.textViewHour?.text = events[position].getHourMins()
        holder?.textViewDrugList?.text = events[position].medList.joinToString("\n")
        holder?.imageViewDelete?.setOnClickListener {
            events.removeAt(position)
            notifyDataSetChanged()
        }
    }

    class EventHolder(v: View) : RecyclerView.ViewHolder(v) {
        var textViewHour: TextView? = null
        var textViewDrugList: TextView? = null
        var imageViewDelete: ImageView? = null

        init {
            textViewHour = v.findViewById(R.id.textViewHour)
            textViewDrugList = v.findViewById(R.id.textViewDrugList)
            imageViewDelete = v.findViewById(R.id.deleteEventImg)
        }
    }

}