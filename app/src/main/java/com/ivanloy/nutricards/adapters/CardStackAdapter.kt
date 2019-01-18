package com.ivanloy.nutricards.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.ivanloy.nutricards.R

class CardStackAdapter(
        private var types: List<String> = emptyList()
) : RecyclerView.Adapter<CardStackAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.food_card, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val type = types[position]
        holder.type.text = type
        holder.itemView.setOnClickListener { v ->
            Toast.makeText(v.context, type, Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return types.size
    }

    fun setSpots(spots: List<String>) {
        this.types = spots
    }

    fun getSpots(): List<String> {
        return types
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val type: TextView = view.findViewById(R.id.card_type)
    }

}
