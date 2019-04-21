package com.prezyk.medcal.model.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "EVENT", foreignKeys = [ForeignKey(
    entity = TimeRange::class,
    parentColumns = ["ID"],
    childColumns = ["TIME_RANGE_ID"],
    onDelete = ForeignKey.CASCADE,
    onUpdate = ForeignKey.CASCADE)], primaryKeys = ["TIME", "TIME_RANGE_ID"])
class Event(@NonNull
            @ColumnInfo(name = "TIME") var time: Long,

            @NonNull
            @ColumnInfo(name = "TIME_RANGE_ID") var eventID: Long) {

    override fun toString(): String {
        return "EventDTO(time=$time, eventID=$eventID)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Event

        if (time != other.time) return false
        if (eventID != other.eventID) return false

        return true
    }

    override fun hashCode(): Int {
        var result = time.hashCode()
        result = 31 * result + eventID.hashCode()
        return result
    }


}