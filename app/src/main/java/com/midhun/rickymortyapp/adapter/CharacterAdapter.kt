package com.midhun.rickymortyapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.midhun.rickymortyapp.databinding.SingleCharacterLayoutBinding
import com.midhun.rickymortyapp.models.CharacterDetails
import com.squareup.picasso.Picasso

class CharacterAdapter(
    private val characterList :List<CharacterDetails>,
    private val callback : onCharacterClick
) : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binder = SingleCharacterLayoutBinding.inflate(
            LayoutInflater.from(parent.context),parent,false
        )
        return CharacterViewHolder(binder)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val characterDetails = characterList.get(position)
        holder.binder.let {
            Picasso.get().load(characterDetails.image).into(it.characterImage)
            it.characterName.text= characterDetails.name
            it.characterStatus.text = characterDetails.status
        }
    }

    override fun getItemCount(): Int {
        return characterList.size
    }

    inner class CharacterViewHolder(val binder : SingleCharacterLayoutBinding) : RecyclerView.ViewHolder(binder.root){
        init {
            binder.root.setOnClickListener {
                callback.onItemClicker(adapterPosition)
            }
        }
    }

    interface onCharacterClick{
        fun onItemClicker(position: Int)
    }

}