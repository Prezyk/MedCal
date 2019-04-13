package com.prezyk.medcal.presenters

import com.prezyk.medcal.model.RecyclerHourModel
import java.util.*
import kotlin.collections.ArrayList

class HourPickPresenter(view: View) {

    lateinit var hours:ArrayList<RecyclerHourModel>
    var selectedDate = Calendar.getInstance()
    var view = view


    fun updateSelectedDate(selectedDate: Calendar) {
        this.selectedDate = selectedDate
        this.hours = setHoursArray()
    }

    fun updateSelectedHours(pickedHours: ArrayList<Calendar>) {

        for(c: Calendar in pickedHours) {
            var tempRecyclerHour = RecyclerHourModel(c)
            for(i in 0 until this.hours.size) {
                if(tempRecyclerHour.equals(this.hours[i])) {
                    this.hours[i].selected = true
                }
            }
        }
    }


    fun onButtonSubmitClick() : ArrayList<Calendar> {
         var selectedHoursList = arrayListOf<Calendar>()
        for(i in 0 until getHoursArray().size) {
            if(hours[i].selected) { selectedHoursList.add(getHoursArray()[i].date) }
        }
        return selectedHoursList
    }


    public interface View {
        fun submit(hours: ArrayList<Calendar>)

    }

    private fun setHoursArray(): ArrayList<RecyclerHourModel> {

        var hours = arrayListOf<RecyclerHourModel>()
        var tempDate = Calendar.getInstance()

        tempDate.set(
            selectedDate.get(Calendar.YEAR),
            selectedDate.get(Calendar.MONTH),
            selectedDate.get(Calendar.DAY_OF_MONTH),
            0,0,0)
        for(i in 0..23) {
            tempDate.add(Calendar.HOUR_OF_DAY, 1)
            hours.add(RecyclerHourModel(tempDate.clone() as Calendar))
        }

        return hours
    }

    fun getHoursArray(): ArrayList<RecyclerHourModel> {
        return this.hours
    }

}