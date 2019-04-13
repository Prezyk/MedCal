package com.prezyk.medcal.model

import java.text.SimpleDateFormat
import java.util.*

class Event {

    companion object {
        val NONE = -1
        val ONCE = 0
        val WEEKLY = 1
        val EVERYDAY = 2
    }

    private lateinit var date: Calendar
    var medList: ArrayList<String>
    var periodic = NONE

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