package com.yayan.pokemonapi.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.yayan.pokemonapi.data.local.PokemonEntity
import com.yayan.pokemonapi.data.local.PokemonLocalRepository
import com.yayan.pokemonapi.data.model.Pokemon
import com.yayan.pokemonapi.data.model.detail.PokemonDetailResponse
import com.yayan.pokemonapi.data.remote.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository,
    private val localRepository: PokemonLocalRepository
) : ViewModel() {

    private val _pokemonDetail = MutableLiveData<PokemonDetailResponse>()
    val pokemonDetail get() = _pokemonDetail

    fun getAllPokemon(){
        viewModelScope.launch {
            pokemonRepository.getAllPokemon().let {
                val data = it.results.map {
                    PokemonEntity(it.name!!, it.url!!)
                }
                data.forEach {
                    localRepository.insertPokemon(it)
                }
            }
        }
    }


    fun getPokemonDetail(url: String){
        viewModelScope.launch {
            pokemonRepository.getPokemonDetail(url).let {
                _pokemonDetail.value = it
            }
        }
    }

    fun getLocalPokemon(): LiveData<List<Pokemon>> {
        return localRepository.getLocalPokemon().map {
            it.map {
                Pokemon(
                    it.name, it.url
                )
            }
        }

    }

}