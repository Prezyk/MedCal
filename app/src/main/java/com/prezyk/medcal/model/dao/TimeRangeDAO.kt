package com.prezyk.medcal.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.prezyk.medcal.model.model.TimeRange

@Dao
public interface TimeRangeDAO {

    @Insert
    fun insert(timeRange: TimeRange): Long

    @Delete
    fun delete(timeRange: TimeRange)

    @Query("SELECT * FROM TIME_RANGE WHERE ID = :id")
    fun findByID(id: Long): TimeRange

    @Query("SELECT * FROM TIME_RANGE WHERE (START_DATE >= :startDate AND END_DATE <= :endDate)")
    fun findAllByRange(startDate: Long, endDate: Long): List<TimeRange>

    @Query("SELECT * FROM TIME_RANGE WHERE START_DATE = :startDate")
    fun findAllByStartDate(startDate: Long): List<TimeRange>

    @Query("SELECT * FROM TIME_RANGE WHERE END_DATE = :endDate")
    fun findAllByEndDate(endDate: Long): List<TimeRange>

    @Query("SELECT * FROM TIME_RANGE ORDER BY ID DESC LIMIT 1")
    fun findMaxID(): TimeRange

    @Query("DELETE FROM TIME_RANGE")
    fun deleteAll()
}