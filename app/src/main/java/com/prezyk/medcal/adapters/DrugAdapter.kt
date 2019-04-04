package com.prezyk.medcal.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.prezyk.medcal.R

class DrugAdapter(context: Context,
    private var dataSource: ArrayList<String>): BaseAdapter() {

    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val rowView: View


        if (position==dataSource.size-1) {
            rowView = inflater.inflate(R.layout.add_event_listview_item2, parent, false)

            var drugName = rowView.findViewById<EditText>(R.id.drugNameEditText)
            var imageView = rowView.findViewById<ImageView>(R.id.addDrugImg)

            imageView.setOnClickListener {
                dataSource.add(drugName.text.toString())
                notifyDataSetChanged()
            }


        } else {

            rowView = inflater.inflate(R.layout.add_event_listview_item, parent, false)
            var drugName = rowView.findViewById<TextView>(R.id.drugNameText)
            var imageView = rowView.findViewById<ImageView>(R.id.deleteDrugImg)
            drugName.text = dataSource[position]

            imageView.setOnClickListener {
                dataSource.removeAt(position)
                notifyDataSetChanged()
            }


        }



        return rowView
    }

    override fun getItem(position: Int): Any {
        return this.dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return this.dataSource.size
    }
}