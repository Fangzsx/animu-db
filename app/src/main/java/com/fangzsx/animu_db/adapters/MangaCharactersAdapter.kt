package com.fangzsx.animu_db.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fangzsx.animu_db.databinding.MangaCharItemBinding
import com.fangzsx.animu_db.models.animecharacters.Data

class MangaCharactersAdapter : RecyclerView.Adapter<MangaCharactersAdapter.CharacterViewHolder>() {

    inner class CharacterViewHolder(val binding : MangaCharItemBinding) : RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<Data>(){
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.character.mal_id == newItem.character.mal_id
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }

    }

    var differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            MangaCharItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = differ.currentList[position].character

        holder.binding.apply {
            Glide
                .with(holder.itemView)
                .load(character.images.jpg.image_url)
                .into(ivMangaChar)

            tvMangaCharName.text = character.name
        }

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}