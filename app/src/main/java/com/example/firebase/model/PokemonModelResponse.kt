package com.example.firebase.model

import com.example.firebase.database.PokemonModel

data class PokemonModelResponse(

    val count: Int,
    val next: String,
    val previous: String,
    val results: List<PokemonModel>
)
