package com.yayan.pokemonapi.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon")
data class PokemonEntity(
    @PrimaryKey val name: String,
    @ColumnInfo(name = "url")
    val url: String
)