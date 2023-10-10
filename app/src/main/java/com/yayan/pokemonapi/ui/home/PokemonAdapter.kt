package com.yayan.pokemonapi.ui.home

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yayan.pokemonapi.data.model.Pokemon
import com.yayan.pokemonapi.databinding.PokemonItemBinding
import com.yayan.pokemonapi.ui.detail.PokemonDetailActivity


class PokemonAdapter : ListAdapter<Pokemon, PokemonAdapter.PokemonViewHolder>(CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val binding = PokemonItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokemonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)
    }

    class PokemonViewHolder(private val binding: PokemonItemBinding) : RecyclerView.ViewHolder(
        binding.root
    ) {
        fun bind(item: Pokemon) {
            with(binding) {
                tvName.text = item.name
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, PokemonDetailActivity::class.java)
                    intent.putExtra("url", item.url)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

    companion object {
        val CALLBACK: DiffUtil.ItemCallback<Pokemon> =
            object : DiffUtil.ItemCallback<Pokemon>() {
                override fun areItemsTheSame(oldUser: Pokemon, newUser: Pokemon): Boolean {
                    return oldUser.name == newUser.name
                }

                @SuppressLint("DiffUtilEquals")
                override fun areContentsTheSame(oldUser: Pokemon, newUser: Pokemon): Boolean {
                    return oldUser == newUser
                }
            }
    }
}
