package com.prezyk.medcal.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.prezyk.medcal.R
import com.prezyk.medcal.model.EventDTO

class RecyclerEventsAdapter(private val eventDTOS: ArrayList<EventDTO>) :  RecyclerView.Adapter<RecyclerEventsAdapter.EventHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerEventsAdapter.EventHolder {
        val row = LayoutInflater.from(parent?.context).inflate(R.layout.display_events_recyclerview_item, parent, false)
        return RecyclerEventsAdapter.EventHolder(row)
    }

    override fun getItemCount(): Int {
        return this.eventDTOS.size
    }

    override fun onBindViewHolder(holder: RecyclerEventsAdapter.EventHolder, position: Int) {
        holder?.textViewHour?.text = eventDTOS[position].getHourMins()
        holder?.textViewDrugList?.text = eventDTOS[position].medList.joinToString("\n")
        holder?.imageViewDelete?.setOnClickListener {
            eventDTOS.removeAt(position)
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