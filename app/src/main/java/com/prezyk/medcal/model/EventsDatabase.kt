package com.prezyk.medcal.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.prezyk.medcal.model.dao.DrugDAO
import com.prezyk.medcal.model.dao.EventDAO
import com.prezyk.medcal.model.dao.TimeRangeDAO
import com.prezyk.medcal.model.model.Drug
import com.prezyk.medcal.model.model.Event
import com.prezyk.medcal.model.model.TimeRange

@Database(entities = [TimeRange::class, Event::class, Drug::class], version = 1)
abstract class EventsDatabase : RoomDatabase() {
    abstract fun drugDao(): DrugDAO
    abstract fun eventDao(): EventDAO
    abstract fun timeRangeDao(): TimeRangeDAO

    companion object {
        var INSTANCE: EventsDatabase? = null

        fun getEventsDatabase(context: Context): EventsDatabase? {
            if(INSTANCE == null) {
                synchronized(EventsDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext, EventsDatabase::class.java, "EventsDB").build()
                }
            }
            return INSTANCE
        }

        fun destroyDatabase() {
            INSTANCE = null
        }

    }

}