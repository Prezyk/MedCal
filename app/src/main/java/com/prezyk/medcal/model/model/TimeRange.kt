package com.prezyk.medcal.model.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.lang.StringBuilder
import java.util.*

@Entity(tableName = "TIME_RANGE")
class TimeRange(@PrimaryKey(autoGenerate = true)
                @NonNull
                @ColumnInfo(name="ID") var eventID: Long? = null,

                @NonNull
                @ColumnInfo(name="START_DATE") var startDate: Long,

                @NonNull
                @ColumnInfo(name="END_DATE") var endDate: Long) {


    override fun toString(): String {
        return "TimeRange(eventID=$eventID, startDate=$startDate, endDate=$endDate)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TimeRange

        if (eventID != other.eventID) return false
        if (startDate != other.startDate) return false
        if (endDate != other.endDate) return false

        return true
    }

    override fun hashCode(): Int {
        var result = eventID?.hashCode() ?: 0
        result = 31 * result + startDate.hashCode()
        result = 31 * result + endDate.hashCode()
        return result
    }


}