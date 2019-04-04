package com.prezyk.medcal.views

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.prezyk.medcal.R
import com.prezyk.medcal.adapters.RecyclerEventsAdapter
import kotlinx.android.synthetic.main.display_events_layout.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import com.prezyk.medcal.model.Event
import com.prezyk.medcal.presenters.DisplayEventsPresenter

class DisplayEventsActivity : AppCompatActivity(), DisplayEventsPresenter.View {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.display_events_layout)



        var selectedDate = Calendar.getInstance()

        selectedDate.timeInMillis = intent.extras.getLong("dateMillis")

        val format = SimpleDateFormat("dd.MM" )
        eventsDate.text = format.format(selectedDate.time)

        var eventList = getEventList()

        viewManager = LinearLayoutManager(this)
        viewAdapter = RecyclerEventsAdapter(eventList)

        recyclerView = findViewById<RecyclerView>(R.id.eventsRecyclerView).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }



//        eventsDate.setOnClickListener {
//            eventList.removeAt(0)
//
//        }

        summonDroneImg.setOnClickListener {
            intent = Intent(this, SummonDroneActivity::class.java)
            startActivity(intent)
        }




//        var eventsAdapter = EventAdapter(this, eventList)
//
//        eventsRecyclerView.adapter = eventsAdapter

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