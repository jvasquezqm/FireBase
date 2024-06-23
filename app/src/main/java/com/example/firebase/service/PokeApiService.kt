package com.example.firebase.service

import com.example.firebase.model.PokemonModelResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PokeApiService {

    @GET("pokemon")
    fun getPokemonList(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Call<PokemonModelResponse> //call retrofit to get the list of pokemons


}