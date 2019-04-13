package com.prezyk.medcal.model

import java.util.*

class RecyclerHourModel(var date: Calendar) {

    var selected = false

    constructor(date: Calendar, selected: Boolean) : this(date){
        this.selected = selected
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as RecyclerHourModel

        if (date.get(Calendar.YEAR) != other.date.get(Calendar.YEAR)) return false
        if (date.get(Calendar.MONTH) != other.date.get(Calendar.MONTH)) return false
        if (date.get(Calendar.DAY_OF_MONTH) != other.date.get(Calendar.DAY_OF_MONTH)) return false
        if (date.get(Calendar.HOUR_OF_DAY) != other.date.get(Calendar.HOUR_OF_DAY)) return false
        if (date.get(Calendar.MINUTE) != other.date.get(Calendar.MINUTE)) return false
        if (selected != other.selected) return false

        return true
    }

    override fun hashCode(): Int {
        var result = date.get(Calendar.YEAR) +
                date.get(Calendar.MONTH) +
                date.get(Calendar.DAY_OF_MONTH) +
                date.get(Calendar.HOUR_OF_DAY) +
                date.get(Calendar.MINUTE)
        result = 31 * result + selected.hashCode()
        return result
    }


}