package com.prezyk.medcal.views

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.prezyk.medcal.R
import com.prezyk.medcal.presenters.MainPresenter
import kotlinx.android.synthetic.main.main_view.*
import java.util.*


class MainActivity() : AppCompatActivity(), MainPresenter.View {

    private val presenter = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_view)



        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->  presenter.updateSelectedDate(GregorianCalendar(year, month, dayOfMonth)) }


        btnDisplayEvents.setOnClickListener {
            presenter.onButtonDisplayEventsClick()
        }


        btnAddEvents.setOnClickListener {
            presenter.onButtonAddEventClick()
        }


    }


    override fun navigateToAddEvent(date: Calendar) {
        var addEventsActivity =
            Intent(this,  AddEventActivity::class.java).apply {
                putExtra("dateMillis", date.timeInMillis)
            }
        startActivity(addEventsActivity)
    }

    override fun navigateToDisplayEvents(date: Calendar) {
        var displayEventsActivity =
            Intent(this,  DisplayEventsActivity::class.java).apply {
                putExtra("dateMillis", date.timeInMillis)
            }
        startActivity(displayEventsActivity)    }


}
