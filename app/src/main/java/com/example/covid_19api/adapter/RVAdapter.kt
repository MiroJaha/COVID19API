package com.example.covid_19api.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.covid_19api.databinding.ItemsViewBinding
import com.example.covid_19api.room.Data

class RVAdapter(private var list: ArrayList<Data>) :
    RecyclerView.Adapter<RVAdapter.ItemViewHolder>() {
    class ItemViewHolder(val binding: ItemsViewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemsViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val data = list[position]

        holder.binding.apply {
            country.text = "Country: ${data.country}"
            confirmed.text = "Confirmed: ${data.confirmed}"
            death.text = "Death: ${data.death}"
        }
    }

    override fun getItemCount() = list.size

    fun update() {
        notifyDataSetChanged()
    }
}