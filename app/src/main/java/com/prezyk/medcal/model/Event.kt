package com.prezyk.medcal.model

import java.text.SimpleDateFormat
import java.util.*

class Event {

    private lateinit var date: Calendar
    var medList: ArrayList<String>
    var isCyclic: Boolean = false

    constructor(date: Calendar, medList: ArrayList<String>, isCyclic: Boolean) {
        this.date = date
        this.medList = medList
        this.isCyclic = isCyclic
    }

    constructor(date: Date, medList: ArrayList<String>, isCyclic: Boolean) {
        this.date = Calendar.getInstance()
        this.date.time = date
        this.medList = medList
        this.isCyclic = isCyclic
    }

    constructor(date: String, medList: ArrayList<String>, isCyclic: Boolean) {
        var format = SimpleDateFormat("dd.MM.YYYY")
        this.date = Calendar.getInstance()
        this.date.time = format.parse(date)
        this.medList = medList
        this.isCyclic = isCyclic
    }

    constructor(year: Int, month: Int, day: Int, hour: Int, minutes: Int, medList: ArrayList<String>, isCyclic: Boolean) {
        this.date = Calendar.getInstance()
        this.date.set(year, month, day, hour, minutes, 0)
        this.medList = medList
        this.isCyclic = isCyclic
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