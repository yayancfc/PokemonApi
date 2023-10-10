package com.yayan.pokemonapi.data.remote

import com.yayan.pokemonapi.data.model.PokemonResponse
import com.yayan.pokemonapi.data.model.detail.PokemonDetailResponse
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val apiService: ApiServices
) {
    suspend fun getAllPokemon(): PokemonResponse {
        return apiService.getPokemon()
    }

    suspend fun getPokemonDetail(url: String): PokemonDetailResponse {
        return apiService.getPokemonDetails(url)
    }
}
