package com.example.firebase.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.firebase.R
import com.example.firebase.model.StadiumModel
import com.squareup.picasso.Picasso

class StadiumAdapter( private var lstStadiums: List<StadiumModel>):
    RecyclerView.Adapter<StadiumAdapter.ViewHolder>(){
        class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
        {
            val tvName: TextView = itemView.findViewById(R.id.tvName)
            val ivStadium:ImageView = itemView.findViewById(R.id.ivStadium)
            val tvLocation:TextView = itemView.findViewById(R.id.tvLocation)
            val tvCapacity:TextView = itemView.findViewById(R.id.tvCapacity)

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_stadium, parent, false))

    }

    override fun getItemCount(): Int {
        return lstStadiums.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemStadium = lstStadiums[position]
        holder.tvName.text = itemStadium.name
       //picasso importar imagen con picasso
        Picasso.get()

            .load(itemStadium.image)
            .resize(250,250)
            .centerCrop()
            .into(holder.ivStadium)
        holder.tvLocation.text = itemStadium.location
        holder.tvCapacity.text = itemStadium.capacity

    }
}