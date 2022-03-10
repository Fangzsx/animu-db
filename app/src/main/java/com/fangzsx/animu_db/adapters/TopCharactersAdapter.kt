package com.fangzsx.animu_db.adapters

import androidx.recyclerview.widget.RecyclerView
import com.fangzsx.animu_db.databinding.TopCharacterItemBinding

class TopCharactersAdapter : RecyclerView.Adapter<TopCharactersAdapter.CharacterViewHolder>() {

    inner class CharacterViewHolder(val binding : TopCharacterItemBinding) : RecyclerView.ViewHolder(binding.root)
}