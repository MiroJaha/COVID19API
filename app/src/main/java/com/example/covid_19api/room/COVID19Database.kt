package com.example.covid_19api.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Data::class],version = 1,exportSchema = false)
abstract class COVID19Database: RoomDatabase() {

    abstract fun covid19Dao(): COVID19Dao

    companion object{
        @Volatile
        var instance: COVID19Database?= null

        fun getInstance(context: Context): COVID19Database{
            if (instance != null)
                return instance!!
            synchronized(this){
                instance= Room.databaseBuilder((context.applicationContext),
                COVID19Database::class.java,
                "MyData")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance!!
        }
    }
}