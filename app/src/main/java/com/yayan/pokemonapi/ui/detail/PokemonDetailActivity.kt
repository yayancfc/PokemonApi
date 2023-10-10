package com.yayan.pokemonapi.ui.detail

import android.R
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.yayan.pokemonapi.databinding.ActivityPokemonDetailBinding
import com.yayan.pokemonapi.ui.home.PokemonViewModel
import com.yayan.pokemonapi.utils.ImageUtils
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PokemonDetailActivity : AppCompatActivity() {

    companion object{
        private const val TAG = "PokemonDetailActivity"
    }

    private lateinit var binding: ActivityPokemonDetailBinding
    private lateinit var pokemonAdapter: PokemonAbilitiesAdapter

    private val viewModel: PokemonViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPokemonDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding){
            setSupportActionBar(myToolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            pokemonAdapter = PokemonAbilitiesAdapter()
            rvAbilities.apply {
                layoutManager = LinearLayoutManager(this@PokemonDetailActivity)
                adapter = pokemonAdapter
            }
        }

        val url = intent.extras!!.getString("url")

        val id = url!!.substringAfter("pokemon").replace("/", "")
        viewModel.getPokemonDetail(id)

        viewModel.pokemonDetail.observe(this){
            with(binding){
                tvName.text = it.name
                pokemonAdapter.submitList(it.abilities)
                ImageUtils.bindToImageView(ivFront, it.sprites.frontDefault)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

}