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
import java.sql.Time
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


//        TODO szajs do Room'a, do wyjebania później
//        val database = EventsDatabase.getEventsDatabase(this)

//        val t = Thread(Runnable {
//            var cal = Calendar.getInstance()
//            cal.add(Calendar.DAY_OF_MONTH, 3)
//
//            var timeRange1 = TimeRange(Calendar.getInstance().timeInMillis, Calendar.getInstance().timeInMillis)
//            var timeRange2 = TimeRange(2L, Calendar.getInstance().timeInMillis, cal.timeInMillis)
//
//            cal = Calendar.getInstance()
//            cal.set(2019, 4, 20, 11, 0)
//            var event1 = EventDTO(cal.timeInMillis, 1L)
//            cal.add(Calendar.HOUR_OF_DAY, 3)
//            var event2 = EventDTO(cal.timeInMillis, 1L)
//
//            var event3 = EventDTO(cal.timeInMillis, 2L)
//            cal.add(Calendar.DAY_OF_MONTH, 1)
//            var event4 = EventDTO(cal.timeInMillis, 2L)
//            cal.add(Calendar.DAY_OF_MONTH, 1)
//            var event5 = EventDTO(cal.timeInMillis, 1)
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
//            var drugList = database?.drugDao()?.findAllByTimeRangeID(1L)

//            for(d: Drug in drugList!!) {
//                Log.e("DRUG NAME: ", d.name)
//            }

//        })

//        t.start()


//        val t = Thread(Runnable {
//            var timeRange = database?.timeRangeDao()?.findMaxID()
//            if(timeRange==null) {
//                Log.d("TIMERANGE", "IS NULL")
//            } else {
//                Log.d("TIMERANGE", "$timeRange")
//                var eventArray = database?.eventDao()?.findAllByTimeRangeID(timeRange!!.eventID!!)
//                var drugs = database?.drugDao()?.findAllByTimeRangeID(timeRange!!.eventID!!)
//
//                if (eventArray!!.isEmpty()) {
//                    Log.d("EVENT", "ARRAY EMPTY")
//                }
//
//                if (drugs!!.isEmpty()) {
//                    Log.d("DRUGS", "DRUG ARRAY EMPTY")
//                }
//
//                Log.d("TIME_RANGE: ", timeRange.toString())
//                for (e: Event in eventArray!!) {
//                    Log.d("EVENT: ", e.toString())
//                }
//
//                for (d: Drug in drugs!!) {
//                    Log.d("DRUG: ", d.toString())
//                }
//            }
//        })
//
//        t.start()
//        t.join()


        val database = EventsDatabase.getEventsDatabase(this)
        val t = Thread(Runnable {

            database?.eventDao()?.deleteAll()
            database?.drugDao()?.deleteAll()
            database?.timeRangeDao()?.deleteAll()

            var trS = Calendar.getInstance()
            trS.set(Calendar.MILLISECOND, 0)
            trS.set(Calendar.SECOND, 0)
            trS.set(Calendar.MINUTE, 0)

            var trE = trS.clone() as Calendar
            trE.add(Calendar.MONTH, 1)

            var timeRange = TimeRange(null, trS.timeInMillis, trE.timeInMillis)

            var trID = database?.timeRangeDao()?.insert(timeRange)

            var eventTime = trS.clone() as Calendar

            var drugList = ArrayList<Drug>()

            drugList.add(Drug("Lek1", trID!!))
            drugList.add(Drug("Lek2", trID!!))
            drugList.add(Drug("Lek3", trID!!))
            drugList.add(Drug("Lek4", trID!!))

            for(d in drugList) {
                database?.drugDao()?.insert(d)
            }



            while(eventTime.timeInMillis < trE.timeInMillis) {
                eventTime.set(Calendar.HOUR_OF_DAY, 8)
                database?.eventDao()?.insert(Event(eventTime.timeInMillis, trID!!))
                
                eventTime.set(Calendar.HOUR_OF_DAY, 12)
                database?.eventDao()?.insert(Event(eventTime.timeInMillis, trID!!))

                eventTime.set(Calendar.HOUR_OF_DAY, 16)
                database?.eventDao()?.insert(Event(eventTime.timeInMillis, trID!!))


                eventTime.add(Calendar.DAY_OF_MONTH, 1)
            }

        })

        t.start()
        t.join()


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
