package com.example.firebase.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.firebase.R
import com.example.firebase.database.PokemonModel
import com.squareup.picasso.Picasso

class PokemonAdapter (private var lstPokemon: List<PokemonModel>):
        RecyclerView.Adapter<PokemonAdapter.ViewHolder>(){
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
         val ivPokemon: ImageView = itemView.findViewById(R.id.ivPokemon)
         val tvPokemonName: TextView = itemView.findViewById(R.id.tvPokemonName)
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_pokemon, parent, false))
    }

    override fun getItemCount(): Int {
        return lstPokemon.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemPokemon = lstPokemon[position]
        holder.tvPokemonName.text = itemPokemon.name
        if (itemPokemon.url.isNotEmpty()) {
            Picasso.get()
                .load(itemPokemon.url)
                .resize(175, 175)
                .centerCrop()
                .into(holder.ivPokemon)
        } else {
            Picasso.get()
                .load(R.drawable.ic_launcher_background)
                .resize(175, 175)
                .centerCrop()
                .into(holder.ivPokemon)
        }
    }
}