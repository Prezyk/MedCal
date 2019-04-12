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
    private lateinit var presenter: DisplayEventsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.display_events_layout)

        presenter = DisplayEventsPresenter(this)
        presenter.setSelectedDate(intent.extras.getLong("dateMillis"))

        eventsDate.text = presenter.prepareDateString()

        viewManager = LinearLayoutManager(this)
        viewAdapter = RecyclerEventsAdapter(presenter.getEventList())

        recyclerView = findViewById<RecyclerView>(R.id.eventsRecyclerView).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        summonDroneImg.setOnClickListener {
            intent = Intent(this, SummonDroneActivity::class.java)
            startActivity(intent)
        }


    }

}