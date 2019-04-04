package com.prezyk.medcal.views

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.prezyk.medcal.adapters.EventAdapter
import com.prezyk.medcal.R
import kotlinx.android.synthetic.main.display_events_layout.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import com.prezyk.medcal.model.Event

class DisplayEventsActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.display_events_layout)



        var selectedDate = Calendar.getInstance()

        selectedDate.timeInMillis = intent.extras.getLong("dateMillis")

        val format = SimpleDateFormat("dd.MM" )
        eventsDate.text = format.format(selectedDate.time)

        var eventList = getEventList()



//        eventsDate.setOnClickListener {
//            eventList.removeAt(0)
//
//        }

        summonDroneImg.setOnClickListener {
            intent = Intent(this, SummonDroneActivity::class.java)
            startActivity(intent)
        }



        var eventsAdapter = EventAdapter(this, eventList)

        eventsListView.adapter = eventsAdapter

//        var imageView = findViewById<ImageView>(R.id.deleteEvent)
//        imageView.setOnClickListener {
//            eventsAdapter.removeItem(imageView.tag as Int)
//            eventsAdapter.notifyDataSetChanged()
//        }

    }

    //TODO getEventList(selectedDate) -> reading events from database
    fun getEventList(): ArrayList<Event> {

        var date1 = Calendar.getInstance()
        var date2 = Calendar.getInstance()
        var date3 = Calendar.getInstance()

        date1.set(2019, 4, 15, 8, 0)
        date2.set(2019, 4, 15, 12, 30)
        date3.set(2019, 4, 15, 16, 0)


        var medList1 = arrayListOf("Wit C", "Wit D", "Paracetamol")
        var medList2 = arrayListOf("Wit C", "Wit B12", "Magnesium", "Paracetamol")
        var medList3 = arrayListOf("Wit C", "Wit D", "Ketonal", "Iron")

        var event1 = Event(date1, medList1)
        var event2 = Event(date2, medList2)
        var event3 = Event(date3, medList3)


        return arrayOf(event1, event2, event3).toCollection(ArrayList())
    }
}