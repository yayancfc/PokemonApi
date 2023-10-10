package com.yayan.pokemonapi.data.model

import com.google.gson.annotations.SerializedName


data class Pokemon(
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("url")
    var url: String? = null
)