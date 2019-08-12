package com.prezyk.medcal.views

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.prezyk.medcal.R
import com.prezyk.medcal.adapters.RecyclerEventsAdapter
import kotlinx.android.synthetic.main.display_events_layout.*
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
        viewAdapter = RecyclerEventsAdapter(presenter.getEventList(this))

        recyclerView = findViewById<RecyclerView>(R.id.eventsRecyclerView).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

//        summonDroneImg.setOnClickListener {
//            intent = Intent(this, SummonDroneActivity::class.java)
//            startActivity(intent)
//        }


    }

}