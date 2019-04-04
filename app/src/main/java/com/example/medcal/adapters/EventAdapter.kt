package com.example.medcal.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.medcal.R
import com.example.medcal.model.Event


class EventAdapter(context: Context,
                   private var dataSource: ArrayList<Event>): BaseAdapter()  {


    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val rowView = inflater.inflate(R.layout.view_events_listview_item, parent, false)

        var textHour = rowView.findViewById(R.id.textViewHour) as TextView
        textHour.text = dataSource[position].getHourMins()
//        var listView = rowView.findViewById(R.id.medsListView) as ListView
//        listView.height = dataSource[position].medList.size*40

        var itemList = dataSource[position].medList as List<String>

        var itemListText = rowView.findViewById(R.id.itemListText) as TextView


        itemListText.text = itemList.joinToString("\n")

        var deleteEventImg = rowView.findViewById(R.id.deleteEventImg) as ImageView

        deleteEventImg.tag = position

        deleteEventImg.setOnClickListener {
            //TODO Dialog window
            this.dataSource.removeAt(deleteEventImg.tag as Int)
            Log.i("BUTTON CLICKED", deleteEventImg.tag.toString() + " : " + this.dataSource.size.toString())
            notifyDataSetChanged()
        }




//        val adapter = ArrayAdapter<String>(this.context, android.R.layout.simple_list_item_1, itemList)
//        listView.adapter = adapter
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