package com.example.covid_19api.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface COVID19Dao {
    @Query("SELECT * FROM `covid-19`")
    fun gettingAllData(): LiveData<List<Data>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addNewData(data: Data)
}