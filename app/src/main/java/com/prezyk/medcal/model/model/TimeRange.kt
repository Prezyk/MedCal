package com.prezyk.medcal.model.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TIME_RANGE")
class TimeRange(@PrimaryKey(autoGenerate = true)
                @NonNull
                @ColumnInfo(name="ID") var eventID: Long,

                @NonNull
                @ColumnInfo(name="START_DATE") var startDate: Long,

                @NonNull
                @ColumnInfo(name="END_DATE") var endDate: Long)