package com.yayan.pokemonapi.ui.home

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.yayan.pokemonapi.R
import com.yayan.pokemonapi.data.model.Pokemon
import com.yayan.pokemonapi.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: PokemonViewModel by viewModels()

    private lateinit var pokemonAdapter: PokemonAdapter
    private lateinit var binding: ActivityMainBinding
    private var listPokemon = listOf<Pokemon>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding){
            setSupportActionBar(myToolbar)
            pokemonAdapter = PokemonAdapter()
            rvPokemon.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = pokemonAdapter
            }
        }

        viewModel.getAllPokemon()
        viewModel.getLocalPokemon().observe(this){
            listPokemon = it
            pokemonAdapter.submitList(listPokemon)
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        val search = menu.findItem(R.id.action_search)
        val searchView = search.actionView as SearchView
        searchView.queryHint = "Search"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                val data = listPokemon.filter {
                    it.name!!.contains(newText!!)
                }
                if (data.isEmpty()) {
                    binding.apply {
                        tvEmpty.visibility = View.VISIBLE
                        rvPokemon.visibility = View.GONE
                    }
                }else {
                    binding.apply {
                        rvPokemon.visibility = View.VISIBLE
                        tvEmpty.visibility = View.GONE
                    }
                    pokemonAdapter.submitList(data)
                }
                return true
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.sort_asc -> {
                val data = listPokemon.sortedBy {
                    it.name
                }
                pokemonAdapter.submitList(data)
                return true
            }

            R.id.sort_desc -> {
                val data = listPokemon.sortedByDescending {
                    it.name
                }
                pokemonAdapter.submitList(data)
                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }
}