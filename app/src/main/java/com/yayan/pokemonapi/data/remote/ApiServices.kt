package com.yayan.pokemonapi.data.remote

import com.yayan.pokemonapi.data.model.PokemonResponse
import com.yayan.pokemonapi.data.model.detail.PokemonDetailResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServices {
    @GET("pokemon")
    suspend fun getPokemon(): PokemonResponse

    @GET("pokemon/{id}")
    suspend fun getPokemonDetails(
        @Path("id") id: String
    ): PokemonDetailResponse

}