package com.yayan.pokemonapi.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PokemonDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPokemon(pokemon: PokemonEntity)
//
//    @Query("SELECT * FROM pokemon WHERE name = :name")
//    suspend fun getPokemonById(name: String): PokemonEntity?

    @Query("SELECT * FROM pokemon")
    fun getAllPokemon(): LiveData<List<PokemonEntity>>
}