package com.yayan.pokemonapi.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PokemonEntity::class], version = 1, exportSchema = false)
abstract class PokemonDatabase : RoomDatabase() {


    companion object {
        fun getInstance(context: Context): PokemonDatabase {
            return Room.databaseBuilder(
                context, PokemonDatabase::class.java,
                "pokemon"
            ).allowMainThreadQueries()
                .build()
        }
    }

    abstract fun getPokemonDAO(): PokemonDAO

}