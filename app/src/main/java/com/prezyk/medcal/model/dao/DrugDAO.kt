package com.prezyk.medcal.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.prezyk.medcal.model.model.Drug

@Dao
public interface DrugDAO {

    @Insert
    fun insert(drug: Drug)

    @Delete
    fun delete(drug: Drug)

    @Query("SELECT * FROM DRUG WHERE TIME_RANGE_ID = :id")
    fun findAllByTimeRangeID(id: Long): List<Drug>

    @Query("DELETE FROM DRUG")
    fun deleteAll()

}