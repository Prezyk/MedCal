package com.prezyk.medcal.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import com.prezyk.medcal.R
import java.text.SimpleDateFormat
import java.util.*

class HoursAdapter(context: Context,
                   private var dataSource: Array<Calendar>) : BaseAdapter() {


    lateinit var context : Context


    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var rowView = inflater.inflate(R.layout.add_event_hours_listview_item, parent, false)

        var checkBox = rowView.findViewById<CheckBox>(R.id.hourOptionCheckBox)
        val format = SimpleDateFormat("HH:mm" )

        checkBox.text = format.format(dataSource[position].time)




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