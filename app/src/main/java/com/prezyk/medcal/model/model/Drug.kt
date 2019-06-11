package com.prezyk.medcal.model.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "DRUG", foreignKeys = [ForeignKey(
    entity = TimeRange::class,
    parentColumns = ["ID"],
    childColumns = ["TIME_RANGE_ID"],
    onDelete = CASCADE,
    onUpdate = CASCADE)], primaryKeys = ["DRUG_NAME", "TIME_RANGE_ID"])
class  Drug(@NonNull
            @ColumnInfo(name="DRUG_NAME") var name: String,

            @NonNull
            @ColumnInfo(name="TIME_RANGE_ID") var eventID: Long) {

    override fun toString(): String {
        return "Drug(name='$name', eventID=$eventID)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Drug

        if (name != other.name) return false
        if (eventID != other.eventID) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + eventID.hashCode()
        return result
    }


}