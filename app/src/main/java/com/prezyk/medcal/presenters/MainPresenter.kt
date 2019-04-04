package com.prezyk.medcal.presenters

import java.util.*


class MainPresenter(view: View) {

    var selectedDate: Calendar = Calendar.getInstance()
    val view = view

    fun updateSelectedDate(selectedDate: Calendar) {
        this.selectedDate = selectedDate
    }

    fun onButtonAddEventClick() {
        this.view.navigateToAddEvent(this.selectedDate)
    }

    fun onButtonDisplayEventsClick() {
        this.view.navigateToDisplayEvents(this.selectedDate)
    }


    public interface View {

        fun navigateToAddEvent(date: Calendar)
        fun navigateToDisplayEvents(date: Calendar)
    }
}