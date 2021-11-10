package com.example.covid_19api.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.covid_19api.room.COVID19Database
import com.example.covid_19api.room.Data
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {

    private val connection = COVID19Database.getInstance(application).covid19Dao()

    fun gettingAllData(): LiveData<List<Data>>{
        return connection.gettingAllData()
    }

    fun addNewData(data: Data){
        CoroutineScope(IO).launch {
            connection.addNewData(data)
        }
    }

}