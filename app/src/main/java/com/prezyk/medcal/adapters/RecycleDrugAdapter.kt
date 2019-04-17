package com.prezyk.medcal.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.prezyk.medcal.R

class RecycleDrugAdapter(private val drugs: ArrayList<String>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    val DRUG_ROW = 0
    val TEXT_FIELD_ROW = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val row: View
        if(viewType==DRUG_ROW) {
            row = LayoutInflater.from(parent?.context).inflate(R.layout.add_event_listview_item, parent, false)
            return DrugTextHolder(row)
        } else {
            row = LayoutInflater.from(parent?.context).inflate(R.layout.add_event_listview_item2, parent, false)
            return DrugTextFieldHolder(row)
        }

    }

    override fun getItemCount(): Int {
        return drugs.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        Log.e("TAG", "STARTING")

        if(getItemViewType(position)==DRUG_ROW) {
            holder as DrugTextHolder
            holder.textViewDrug?.text = drugs[position]
            holder.imageViewDelete?.setOnClickListener {
                drugs.removeAt(position)
                notifyDataSetChanged()
            }

        } else if (getItemViewType(position)==TEXT_FIELD_ROW) {
            holder as DrugTextFieldHolder
            holder.imageViewAdd?.setOnClickListener {
                drugs.add(holder.textFieldDrug?.text.toString())
                holder.textFieldDrug?.setText("")
                notifyDataSetChanged()
            }
        }


    }

    override fun getItemViewType(position: Int): Int {
        return if(position==0 || drugs.isEmpty()) TEXT_FIELD_ROW else DRUG_ROW
    }

    class DrugTextHolder(view: View): RecyclerView.ViewHolder(view) {
        var textViewDrug: TextView? = null
        var imageViewDelete: ImageView? = null

        init {
            textViewDrug = view.findViewById(R.id.drugNameText)
            imageViewDelete = view.findViewById(R.id.deleteDrugImg)
        }

    }


    class DrugTextFieldHolder(view: View): RecyclerView.ViewHolder(view) {
        var textFieldDrug: EditText? = null
        var imageViewAdd: ImageView? = null

        init {
            textFieldDrug = view.findViewById(R.id.drugNameEditText)
            imageViewAdd = view.findViewById(R.id.addDrugImg)
        }

    }
}