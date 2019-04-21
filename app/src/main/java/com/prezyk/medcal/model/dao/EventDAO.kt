package com.prezyk.medcal.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.prezyk.medcal.model.model.Event

@Dao
public interface EventDAO {

    @Insert
    fun insert(event: Event): Long

    @Delete
    fun delete(event: Event)

    @Query("SELECT * FROM EVENT WHERE TIME_RANGE_ID = :id")
    fun findAllByTimeRangeID(id: Long): List<Event>

    @Query("SELECT * FROM EVENT WHERE (TIME >= :start AND TIME <= :end)")
    fun findAllInRange(start: Long, end: Long): List<Event>

    @Query("DELETE FROM EVENT")
    fun deleteAll()

}