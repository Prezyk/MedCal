package com.prezyk.medcal.presenters

import android.content.Context
import com.prezyk.medcal.model.EventDTO
import com.prezyk.medcal.model.EventsDatabase
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

    fun getEventList(context: Context): ArrayList<EventDTO> {


        var minTime = this.selectedDate.clone() as Calendar
        minTime.set(Calendar.HOUR_OF_DAY, 0)
        minTime.set(Calendar.MINUTE, 0)
        minTime.set(Calendar.SECOND, 0)
        minTime.set(Calendar.MILLISECOND, 0)

        var maxTime = minTime.clone() as Calendar
        maxTime.add(Calendar.DAY_OF_MONTH, 1)

        var database = EventsDatabase.getEventsDatabase(context)
        var eventDTOList = ArrayList<EventDTO>()

        var t = Thread(Runnable {

            var eventList = database?.eventDao()?.findAllInRange(minTime.timeInMillis, maxTime.timeInMillis)
            for(e in eventList!!) {
                var drugList = database?.drugDao()?.findAllByTimeRangeID(e.eventID)
                var time = Calendar.getInstance()
                time.timeInMillis = e.time
                eventDTOList.add(EventDTO(e.time, drugList!!))
            }
            eventDTOList.sortedWith(compareBy { it.date.timeInMillis })
        })

        t.start()
        t.join()

//        var date1 = Calendar.getInstance()
//        var date2 = Calendar.getInstance()
//        var date3 = Calendar.getInstance()
//
//        date1.set(2019, 4, 15, 8, 0)
//        date2.set(2019, 4, 15, 12, 30)
//        date3.set(2019, 4, 15, 16, 0)
//
//
//        var medList1 = arrayListOf("Wit C", "Wit D", "Paracetamol")
//        var medList2 = arrayListOf("Wit C", "Wit B12", "Magnesium", "Paracetamol")
//        var medList3 = arrayListOf("Wit C", "Wit D", "Ketonal", "Iron")
//
//        var event1 = EventDTO(date1, medList1, EventDTO.NONE)
//        var event2 = EventDTO(date2, medList2, EventDTO.NONE)
//        var event3 = EventDTO(date3, medList3, EventDTO.NONE)


//        return arrayOf(event1, event2, event3).toCollection(ArrayList())
        return eventDTOList
    }




    public interface View {

    }
}