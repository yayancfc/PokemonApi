package com.yayan.pokemonapi.data.model.detail



data class PokemonDetailResponse(
    val abilities: List<Ability>,
    val name: String,
    val sprites: Sprites,
)