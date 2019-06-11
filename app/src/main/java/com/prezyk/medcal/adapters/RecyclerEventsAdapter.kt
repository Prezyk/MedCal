package com.prezyk.medcal.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.prezyk.medcal.R
import com.prezyk.medcal.model.EventDTO
import androidx.appcompat.app.AlertDialog
import android.widget.CheckBox
import com.prezyk.medcal.model.EventsDatabase
import com.prezyk.medcal.model.model.Drug
import com.prezyk.medcal.model.model.Event


class RecyclerEventsAdapter(private var eventDTOS: ArrayList<EventDTO>) :  RecyclerView.Adapter<RecyclerEventsAdapter.EventHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerEventsAdapter.EventHolder {
        val row = LayoutInflater.from(parent?.context).inflate(R.layout.display_events_recyclerview_item, parent, false)
        this.context = parent?.context
        return RecyclerEventsAdapter.EventHolder(row)
    }

    override fun getItemCount(): Int {
        return this.eventDTOS.size
    }

    override fun onBindViewHolder(holder: RecyclerEventsAdapter.EventHolder, position: Int) {
        holder?.textViewHour?.text = eventDTOS[position].getHourMins()
        holder?.textViewDrugList?.text = eventDTOS[position].medList.joinToString("\n")
        holder?.imageViewDelete?.setOnClickListener {
//            var database = EventsDatabase.getEventsDatabase(context)
//            val t = Thread(Runnable {
//
//
//            })

            val checkBoxView = View.inflate(this.context, R.layout.dialog_delete_event, null)
            val checkBox = checkBoxView.findViewById<View>(R.id.deleteAllCheckBox) as CheckBox

            val builder = AlertDialog.Builder(this.context)
            builder.setTitle("Usunięcie zdarzenia")
            builder.setMessage("Żeby usunąć wszystkie wystąpienia zdarzenia zaznacz opcję")
                .setView(checkBoxView)
                .setCancelable(true)
                .setPositiveButton("Usuń", { dialog, id ->
                    var database = EventsDatabase.getEventsDatabase(this.context)
                    if(checkBox.isChecked) {
                        var t = Thread(Runnable {
                            var timeRange = database?.timeRangeDao()?.findByID(eventDTOS[position].ID!!)
                            if (timeRange != null) {
                                database?.timeRangeDao()?.delete(timeRange)
                            }
                        })
                        t.start()
                        t.join()

                        var tempList = ArrayList<EventDTO>()
                        var ID = eventDTOS[position].ID
                        for(i in 0 until eventDTOS.size) {
                            if(eventDTOS[i].ID==ID) {
                                tempList.add(eventDTOS[i])
                            }
                        }
                        eventDTOS.removeAll(tempList)


                    } else {

                        var event = Event(eventDTOS[position].date.timeInMillis, eventDTOS[position].ID!!)
                        var t = Thread(Runnable {
                            database?.eventDao()?.delete(event)
                            dialog.cancel()
                        })
                        t.start()
                        t.join()
                        eventDTOS.removeAt(position)
                    }

                    notifyDataSetChanged()
                    dialog.dismiss()


                })
                .setNegativeButton("Zamknij", { dialog, id -> dialog.cancel() }).show()


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