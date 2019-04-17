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
            @ColumnInfo(name = "TIME_RANGE_ID") var eventID: Long)