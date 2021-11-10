package com.example.covid_19api

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.covid_19api.adapter.RVAdapter
import com.example.covid_19api.api.APIData
import com.example.covid_19api.room.Data
import com.example.covid_19api.view_model.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class show_data : Fragment() {

    private val mainViewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view= inflater.inflate(R.layout.fragment_show_data, container, false)

        var list = arrayListOf<Data>()
        val rv=view.findViewById<RecyclerView>(R.id.mainRV)

        CoroutineScope(IO).launch {
            list= APIData.gettingAPIArray()
            withContext(Main){
                rv.adapter= RVAdapter(list)
                rv.layoutManager = LinearLayoutManager(context)
            }
        }

        val old = arrayListOf<Data>()
        mainViewModel.gettingAllData().observe(viewLifecycleOwner){
            old.addAll(it)
        }
        view.findViewById<Button>(R.id.button).setOnClickListener{
            if (old.isEmpty()) {
                for (data in list)
                    mainViewModel.addNewData(data)
            }
            else{
                Toast.makeText(context,"Data Already Saved",Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

}