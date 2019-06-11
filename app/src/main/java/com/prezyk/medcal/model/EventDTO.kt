package com.prezyk.medcal.model

import com.prezyk.medcal.model.model.Drug
import com.prezyk.medcal.model.model.Event
import com.prezyk.medcal.model.model.TimeRange
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class EventDTO {

    companion object {
        val NONE = -1
        val ONCE = 0
        val WEEKLY = 1
        val EVERYDAY = 2

        fun buildEventDTOList(drugList: List<Drug>, eventList: List<Event>): List<EventDTO?> {

            var eventDTOList = arrayOfNulls<EventDTO>(eventList.size)
            for(i in 0 until eventDTOList.size) {
                var time = eventList[i].time
                eventDTOList[i] = EventDTO(time, drugList, eventList[i].eventID)
            }

            return eventDTOList.asList()
        }


    }

    lateinit var date: Calendar
    var medList: ArrayList<String>
    var periodic = NONE
    var ID: Long? = null

    constructor(date: Calendar, medList: ArrayList<String>) {
        this.date = date
        this.medList = medList
        this.periodic = NONE
    }

    constructor(date: Long, medList: List<Drug>, ID: Long) {
        this.date = Calendar.getInstance()
        this.date.timeInMillis = date
        this.ID = ID
        this.medList = ArrayList<String>()
        for(med in medList) {
            this.medList.add(med.name)
        }
    }

    constructor(date: Calendar, medList: ArrayList<String>, periodic: Int) {
        this.date = date
        this.medList = medList
        this.periodic = periodic
    }

    constructor(date: Date, medList: ArrayList<String>, periodic: Int) {
        this.date = Calendar.getInstance()
        this.date.time = date
        this.medList = medList
        this.periodic = periodic
    }

    constructor(date: String, medList: ArrayList<String>, periodic: Int) {
        var format = SimpleDateFormat("dd.MM.YYYY")
        this.date = Calendar.getInstance()
        this.date.time = format.parse(date)
        this.medList = medList
        this.periodic = periodic
    }

    constructor(year: Int, month: Int, day: Int, hour: Int, minutes: Int, medList: ArrayList<String>, periodic: Int) {
        this.date = Calendar.getInstance()
        this.date.set(year, month, day, hour, minutes, 0)
        this.medList = medList
        this.periodic = periodic
    }


    fun addMed(med: String) {
        this.medList.add(med)
    }

    fun addMeds(medList: Array<String>) {
        this.medList.addAll(medList)
    }

    fun getHourMins(): String {

        val format = SimpleDateFormat("HH:mm")
        return format.format(this.date.time)
    }

//    fun getMedList() : ArrayList<String> {
//        return this.medList
//    }








}