package com.yayan.pokemonapi.data.local

import androidx.lifecycle.LiveData
import javax.inject.Inject

class PokemonLocalRepository @Inject constructor(private val pokemonDAO: PokemonDAO) {
    fun getLocalPokemon(): LiveData<List<PokemonEntity>> {
        return pokemonDAO.getAllPokemon()
    }

    fun insertPokemon(pokemonEntity: PokemonEntity){
        pokemonDAO.insertPokemon(pokemonEntity)
    }
}