package com.prezyk.medcal.presenters

import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class AddEventPresenter(view: View) {

    val NONE = -1
    val ONCE = 0
    val WEEKLY = 1
    val EVERYDAY = 2

    val view = view
    var selectedStartDate = Calendar.getInstance()
    var selectedEndDate = Calendar.getInstance()
    var periodic = NONE
    private val format = SimpleDateFormat("dd/MM/yyyy")

    var selectedHours = ArrayList<Calendar>()
    var drugList = arrayListOf("")

//    companion object {
//        val NONE = -1
//        val ONCE = 0
//        val WEEKLY = 1
//        val EVERYDAY = 2
//    }





    fun setSelectedDate(dateMillis: Long) {
        this.selectedStartDate.timeInMillis = dateMillis
        this.selectedEndDate.timeInMillis = dateMillis

        view.setStartDateTextView(format.format(this.selectedStartDate.time))
        view.setEndDateTextView(format.format(this.selectedEndDate.time))
    }

    fun updateSelectedStartDate(dateMillis: Long) {
        this.selectedStartDate.timeInMillis = dateMillis
        view.setStartDateTextView(format.format(this.selectedStartDate.time))
    }

    fun updateSelectedStartDate(year: Int, month: Int, day: Int) {
        this.selectedStartDate.set(year, month, day)
        view.setStartDateTextView(format.format(this.selectedStartDate.time))
    }

    fun updateSelectedEndDate(year: Int, month: Int, day: Int) {
        this.selectedEndDate.set(year, month, day)
        view.setEndDateTextView(format.format(this.selectedEndDate.time))
    }

    fun updateSelectedEndDate(dateMillis: Long) {
        this.selectedEndDate.timeInMillis = dateMillis
        view.setEndDateTextView(format.format(this.selectedEndDate.time))
    }

    fun onStartDateTextViewClick() {
        view.showStartDatePicker(this.selectedStartDate.get(Calendar.YEAR),
            this.selectedStartDate.get(Calendar.MONTH),
            this.selectedStartDate.get(Calendar.DAY_OF_MONTH))
    }

    fun onEndDateTextViewClick() {
        view.showEndDatePicker(this.selectedEndDate.get(Calendar.YEAR),
            this.selectedStartDate.get(Calendar.MONTH),
            this.selectedEndDate.get(Calendar.DAY_OF_MONTH))
    }

    fun onPickHoursButtonClick() {
        view.navigateToPickHours(this.selectedStartDate.timeInMillis)
    }

    fun onSubmitButtonClick() {}

    fun updatePeriodicSelection(id: Int) {
        this.periodic = when (id) {
            ONCE -> ONCE
            WEEKLY -> WEEKLY
            EVERYDAY -> EVERYDAY
            else -> NONE
        }
    }

    fun updateHoursPicked(hoursList: ArrayList<Calendar>) {
        this.selectedHours = hoursList
    }

    public interface View {
        fun setStartDateTextView(text: String)
        fun setEndDateTextView(text: String)
        fun showStartDatePicker(year: Int, month: Int, day: Int)
        fun showEndDatePicker(year: Int, month: Int, day: Int)
        fun navigateToPickHours(date: Long)
        fun submit()
    }
}