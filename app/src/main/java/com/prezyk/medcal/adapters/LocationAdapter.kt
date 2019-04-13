package com.prezyk.medcal.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.prezyk.medcal.R

class LocationAdapter(context: Context, private val dataSource : Array<String>) : BaseAdapter() {

    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var rowView = inflater.inflate(R.layout.location_dropdown, parent, false)
        var locationNameTextView = rowView.findViewById<TextView>(R.id.locationNameTextView)
        locationNameTextView.text = dataSource[position]

        return rowView
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return dataSource.size
    }
}