package com.prezyk.medcal.presenters

import android.content.Context
import com.prezyk.medcal.model.EventsDatabase
import com.prezyk.medcal.model.model.Drug
import com.prezyk.medcal.model.model.Event
import com.prezyk.medcal.model.model.TimeRange
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






    fun setSelectedDate(dateMillis: Long) {
        this.selectedStartDate.timeInMillis = dateMillis
        this.selectedEndDate.timeInMillis = dateMillis

        view.setStartDateTextView(format.format(this.selectedStartDate.time))
        view.setEndDateTextView(format.format(this.selectedEndDate.time))
    }

    fun updateSelectedStartDate(dateMillis: Long) {
        this.selectedStartDate.timeInMillis = dateMillis
        if(this.selectedStartDate.timeInMillis>this.selectedEndDate.timeInMillis) {
            this.selectedEndDate.timeInMillis = dateMillis
            view.setEndDateTextView(format.format(this.selectedEndDate.time))
        }
        view.setStartDateTextView(format.format(this.selectedStartDate.time))
    }

    fun updateSelectedStartDate(year: Int, month: Int, day: Int) {
        this.selectedStartDate.set(year, month, day)
        if(this.selectedStartDate.timeInMillis>this.selectedEndDate.timeInMillis) {
            this.selectedEndDate.set(year, month, day)
            view.setEndDateTextView(format.format(this.selectedEndDate.time))
        }
        view.setStartDateTextView(format.format(this.selectedStartDate.time))
    }

    fun updateSelectedEndDate(year: Int, month: Int, day: Int) {
        this.selectedEndDate.set(year, month, day)
        if(this.selectedEndDate.timeInMillis < this.selectedStartDate.timeInMillis) {
            this.selectedStartDate.set(year, month, day)
            view.setStartDateTextView(format.format(this.selectedStartDate.time))
        }
        view.setEndDateTextView(format.format(this.selectedEndDate.time))
    }

    fun updateSelectedEndDate(dateMillis: Long) {
        this.selectedEndDate.timeInMillis = dateMillis
        if(this.selectedEndDate.timeInMillis < this.selectedStartDate.timeInMillis) {
            this.selectedStartDate.timeInMillis = dateMillis
            view.setStartDateTextView(format.format(this.selectedStartDate.time))
        }
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

    fun onSubmitButtonClick() {
        if(periodic==NONE || periodic>2 || periodic<-1) {
            view.showSumbissionErrorToast()
        } else {
            view.submit()
        }
    }

    fun saveDataToDatabase(context: Context) {

        val database = EventsDatabase.getEventsDatabase(context)


        val t = Thread(Runnable {

//            database?.eventDao()?.deleteAll()
//            database?.drugDao()?.deleteAll()
//            database?.timeRangeDao()?.deleteAll()


            this.selectedStartDate.set(Calendar.MILLISECOND, 0)
            this.selectedStartDate.set(Calendar.SECOND, 0)
            this.selectedEndDate.set(Calendar.MILLISECOND, 0)
            this.selectedEndDate.set(Calendar.SECOND, 0)

            var timeRangeDB = TimeRange(null, this.selectedStartDate.timeInMillis, this.selectedEndDate.timeInMillis)

            var timeRangeID = database?.timeRangeDao()?.insert(timeRangeDB)

            var drugListDB: ArrayList<Drug> = ArrayList()

            for(s: String in drugList) {
                database?.drugDao()?.insert(Drug(s, timeRangeID!!))
            }


            for(c: Calendar in selectedHours) {
                c.set(Calendar.MILLISECOND, 0)
                c.set(Calendar.SECOND, 0)
            }

            selectedHours.sortedWith(compareBy { it.timeInMillis })


            var eventTime = this.selectedStartDate
            this.selectedEndDate.set(Calendar.HOUR_OF_DAY, 0)
            this.selectedEndDate.set(Calendar.MINUTE, 0)
            this.selectedEndDate.add(Calendar.DAY_OF_MONTH, 1)

            if(periodic==ONCE || periodic==EVERYDAY) {
                while (eventTime.timeInMillis < this.selectedEndDate.timeInMillis) {
                    eventTime.set(Calendar.MINUTE, 0)
                    eventTime.set(Calendar.HOUR_OF_DAY, 0)
                    for (c: Calendar in selectedHours) {
                        eventTime.set(Calendar.HOUR_OF_DAY, c.get(Calendar.HOUR_OF_DAY))
                        eventTime.set(Calendar.MINUTE, c.get(Calendar.MINUTE))
                        database?.eventDao()?.insert(Event(eventTime.timeInMillis, timeRangeID!!))
                    }
                    eventTime.add(Calendar.DAY_OF_MONTH, 1)
                }
            } else if(periodic==WEEKLY) {
                while (eventTime.timeInMillis < this.selectedEndDate.timeInMillis) {
                    eventTime.set(Calendar.HOUR_OF_DAY, 0)
                    eventTime.set(Calendar.MINUTE, 0)
                    for (c: Calendar in selectedHours) {
                        eventTime.set(Calendar.HOUR_OF_DAY, c.get(Calendar.HOUR_OF_DAY))
                        eventTime.set(Calendar.MINUTE, c.get(Calendar.MINUTE))
                        database?.eventDao()?.insert(Event(eventTime.timeInMillis, timeRangeID!!))
                    }
                    eventTime.add(Calendar.DAY_OF_MONTH, 7)
                }
            }
        })
        t.start()
        t.join()


    }

    fun updatePeriodicSelection(id: Int) {

        if(this.periodic!=id && id==ONCE) {
            view.hideEndDateTextView()
        } else if(this.periodic!=id && id!=ONCE) {
            view.showEndDateTextView()
        }

        this.periodic = when (id) {
            ONCE -> ONCE
            WEEKLY -> WEEKLY
            EVERYDAY -> EVERYDAY
            else -> NONE
        }
    }

    fun updateHoursPicked(hoursList: ArrayList<Calendar>) {
        this.selectedHours = hoursList
        var timeFormat = SimpleDateFormat("HH:mm")
        var pickedHoursStringList = ArrayList<String>()
        for (c: Calendar in hoursList) {
            pickedHoursStringList.add(timeFormat.format(c.time))
        }
        view.updatePickedHoursTextView(pickedHoursStringList)
    }

    public interface View {
        fun setStartDateTextView(text: String)
        fun setEndDateTextView(text: String)
        fun showStartDatePicker(year: Int, month: Int, day: Int)
        fun showEndDatePicker(year: Int, month: Int, day: Int)
        fun updatePickedHoursTextView(pickedHours: ArrayList<String>)
        fun navigateToPickHours(date: Long)
        fun hideEndDateTextView()
        fun showEndDateTextView()
        fun showSumbissionErrorToast()
        fun submit()
    }
}