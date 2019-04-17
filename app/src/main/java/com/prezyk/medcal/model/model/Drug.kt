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
    onDelete = ForeignKey.CASCADE,
    onUpdate = ForeignKey.CASCADE)], primaryKeys = ["DRUG_NAME", "TIME_RANGE_ID"])
class  Drug(@NonNull
            @ColumnInfo(name="DRUG_NAME") var name: String,

            @NonNull
            @ColumnInfo(name="TIME_RANGE_ID") var eventID: Long)