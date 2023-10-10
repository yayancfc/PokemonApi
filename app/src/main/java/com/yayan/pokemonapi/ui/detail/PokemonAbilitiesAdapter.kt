package com.yayan.pokemonapi.ui.detail

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yayan.pokemonapi.data.model.detail.Ability
import com.yayan.pokemonapi.databinding.PokemonItemBinding


class PokemonAbilitiesAdapter : ListAdapter<Ability, PokemonAbilitiesAdapter.PokemonAbilitiesViewHolder>(
    CALLBACK
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonAbilitiesViewHolder {
        val binding = PokemonItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokemonAbilitiesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonAbilitiesViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)
    }

    class PokemonAbilitiesViewHolder(private val binding: PokemonItemBinding) : RecyclerView.ViewHolder(
        binding.root
    ) {
        fun bind(item: Ability) {
            with(binding) {
                tvName.text = item.ability.name
            }
        }
    }

    companion object {
        val CALLBACK: DiffUtil.ItemCallback<Ability> =
            object : DiffUtil.ItemCallback<Ability>() {
                override fun areItemsTheSame(oldUser: Ability, newUser: Ability): Boolean {
                    return oldUser.ability.name == newUser.ability.name
                }

                @SuppressLint("DiffUtilEquals")
                override fun areContentsTheSame(oldUser: Ability, newUser: Ability): Boolean {
                    return oldUser == newUser
                }
            }
    }
}
