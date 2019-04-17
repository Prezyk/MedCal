package com.prezyk.medcal.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.prezyk.medcal.R
import com.prezyk.medcal.model.RecyclerHourModel
import java.text.SimpleDateFormat
import java.util.*

class RecyclerHoursAdapter(private val hours: ArrayList<RecyclerHourModel>): RecyclerView.Adapter<RecyclerHoursAdapter.HoursViewHolder>() {

//    var hoursState = BooleanArray(hours.size)
    val format = SimpleDateFormat("HH:mm")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerHoursAdapter.HoursViewHolder {
        val row = LayoutInflater.from(parent?.context).inflate(R.layout.add_event_hours_listview_item, parent, false)
        return RecyclerHoursAdapter.HoursViewHolder(row)

    }

    override fun getItemCount(): Int {
        return this.hours.size
    }

    override fun onBindViewHolder(holder: RecyclerHoursAdapter.HoursViewHolder, position: Int) {
        holder?.checkBox.text = format.format(hours[position].date.time)
        holder?.checkBox.setOnCheckedChangeListener(null)
        holder?.checkBox.isChecked = hours[position].selected
        holder?.checkBox.setOnCheckedChangeListener { _, isChecked -> hours[position].selected = isChecked }


    }


    class HoursViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var checkBox = v.findViewById<CheckBox>(R.id.hourOptionCheckBox)!!
    }
}