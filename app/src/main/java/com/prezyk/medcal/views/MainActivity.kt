package com.prezyk.medcal.views

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.prezyk.medcal.R
import com.prezyk.medcal.model.EventsDatabase
import com.prezyk.medcal.model.model.Drug
import com.prezyk.medcal.model.model.Event
import com.prezyk.medcal.model.model.TimeRange
import com.prezyk.medcal.presenters.MainPresenter
import kotlinx.android.synthetic.main.main_view.*
import java.util.*


class MainActivity : AppCompatActivity(), MainPresenter.View {

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


        //TODO szajs do Room'a, do wyjebania później
        val database = EventsDatabase.getEventsDatabase(this)

        val t = Thread(Runnable {
//            var cal = Calendar.getInstance()
//            cal.add(Calendar.DAY_OF_MONTH, 3)
//
//            var timeRange1 = TimeRange(1L, Calendar.getInstance().timeInMillis, Calendar.getInstance().timeInMillis)
//            var timeRange2 = TimeRange(2L, Calendar.getInstance().timeInMillis, cal.timeInMillis)
//
//            cal = Calendar.getInstance()
//            cal.set(2019, 4, 20, 11, 0)
//            var event1 = Event(cal.timeInMillis, 1L)
//            cal.add(Calendar.HOUR_OF_DAY, 3)
//            var event2 = Event(cal.timeInMillis, 1L)
//
//            var event3 = Event(cal.timeInMillis, 2L)
//            cal.add(Calendar.DAY_OF_MONTH, 1)
//            var event4 = Event(cal.timeInMillis, 2L)
//            cal.add(Calendar.DAY_OF_MONTH, 1)
//            var event5 = Event(cal.timeInMillis, 1)
//
//            var drug1 = Drug("Paracetamol", 1L)
//            var drug2 = Drug("Ibuprofen", 1L)
//
//            var drug3 = Drug("Ketonal", 2L)
//            var drug4 = Drug("Pseudoefedryna",2L)
//            var drug5 = Drug("Witamina C", 2L)
//
//            database?.timeRangeDao()?.insert(timeRange1)
//            database?.timeRangeDao()?.insert(timeRange2)
//            database?.eventDao()?.insert(event1)
//            database?.eventDao()?.insert(event2)
//            database?.eventDao()?.insert(event3)
//            database?.eventDao()?.insert(event4)
//            database?.eventDao()?.insert(event5)
//
//            database?.drugDao()?.insert(drug1)
//            database?.drugDao()?.insert(drug2)
//            database?.drugDao()?.insert(drug3)
//            database?.drugDao()?.insert(drug4)
//            database?.drugDao()?.insert(drug5)
            var drugList = database?.drugDao()?.findAllByTimeRangeID(1L)

            for(d: Drug in drugList!!) {
                Log.e("DRUG NAME: ", d.name)
            }

        })

        t.start()



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
