package com.example.covid_19api.api

import android.util.Log
import com.example.covid_19api.room.Data
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import org.json.JSONObject
import java.net.URL

class APIData {

    private val list= arrayListOf<Data>()

    init {
        CoroutineScope(IO).launch {
            val data = async { getData() }.await()

            if (data.isNotEmpty()) {
                addDataToArray(data)
            }
        }
    }

    private fun getData(): String {

        return try {
            URL("https://covid-api.mmediagroup.fr/v1/cases").readText(Charsets.UTF_8)
        } catch (e: Exception) {
            "Unable to get Data: $e"
        }
    }

    private fun addDataToArray(result: String) {
        Log.d("Error XXX",result)
        try{
            val jsonObject = JSONObject(result)
            val countries = jsonObject.keys()
            for (country in countries) {
                val jsonObject2 = jsonObject.getJSONObject(country)
                val confirmed = jsonObject2.getJSONObject("All").getInt("confirmed")
                val death = jsonObject2.getJSONObject("All").getInt("deaths")
                list.add(Data(0, country, confirmed, death))
            }
        }
        catch (e:Exception){
            Log.d("Error XXX","Error On Parsing Data $e")
        }
    }

    companion object{
        private var apiData: APIData? =null

        private var listOfData= arrayListOf<Data>()

        private fun fitchData(): APIData? {
            if (apiData == null){
                apiData = APIData()
            }
            return apiData
        }

        suspend fun gettingAPIArray(): ArrayList<Data>{
            return withContext(Main) {
                if (listOfData.isEmpty())
                    listOfData = fitchData()!!.list
                return@withContext listOfData
            }
        }
    }
}