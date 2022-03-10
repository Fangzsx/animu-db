package com.fangzsx.animu_db.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fangzsx.animu_db.databinding.TopCharacterItemBinding
import com.fangzsx.animu_db.models.topcharacters.Data

class TopCharactersAdapter : RecyclerView.Adapter<TopCharactersAdapter.CharacterViewHolder>() {

    inner class CharacterViewHolder(val binding : TopCharacterItemBinding) : RecyclerView.ViewHolder(binding.root)

    var onItemClick : ((Data) -> Unit)? = null

    private val differCallback = object : DiffUtil.ItemCallback<Data>(){
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.mal_id == newItem.mal_id
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }
    }

    var differ = AsyncListDiffer(this, differCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            TopCharacterItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = differ.currentList[position]

        holder.binding.apply {
            Glide
                .with(holder.itemView)
                .load(character.images.jpg.image_url)
                .into(ivTopAnimeChar)
            tvTopAnimeCharName.text = character.name
        }

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(character)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}