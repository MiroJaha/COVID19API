package com.example.covid_19api

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.covid_19api.api.APIData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class call_data : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view= inflater.inflate(R.layout.fragment_call_data, container, false)

        view.findViewById<Button>(R.id.button2).setOnClickListener {
            CoroutineScope(IO).launch {
                val list = APIData.gettingAPIArray()
                withContext(Main){
                    if (list.isEmpty()) {
                        view.findViewById<Button>(R.id.button2).performClick()
                    } else {
                        Navigation.findNavController(view).navigate(R.id.action_call_data_to_show_data)
                    }
                }

            }
        }

        return view
    }

}