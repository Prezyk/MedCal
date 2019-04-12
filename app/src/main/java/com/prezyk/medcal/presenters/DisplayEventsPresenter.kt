package com.prezyk.medcal.presenters

import android.util.EventLog
import com.prezyk.medcal.model.Event
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class DisplayEventsPresenter(view: View) {

    lateinit var selectedDate : Calendar

    fun setSelectedDate(date: Long) {
        this.selectedDate = Calendar.getInstance()
        selectedDate.timeInMillis = date
    }

    fun prepareDateString(): String {
        val format = SimpleDateFormat("dd.MM")
        return format.format(this.selectedDate.time).toString()
    }

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

        var event1 = Event(date1, medList1, false)
        var event2 = Event(date2, medList2, false)
        var event3 = Event(date3, medList3, false)


        return arrayOf(event1, event2, event3).toCollection(ArrayList())
    }




    public interface View {

    }
}