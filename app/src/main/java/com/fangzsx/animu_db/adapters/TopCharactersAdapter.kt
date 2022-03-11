package com.fangzsx.animu_db.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fangzsx.animu_db.R
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

        when(position){
            0 -> holder.binding.tvTopAnimeCharRanking.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.gold))
            1 -> holder.binding.tvTopAnimeCharRanking.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.silver))
            2 -> holder.binding.tvTopAnimeCharRanking.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.bronze))
            else -> holder.binding.tvTopAnimeCharRanking.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.gray))


        }

        holder.binding.apply {
            Glide
                .with(holder.itemView)
                .load(character.images.jpg.image_url)
                .into(ivTopAnimeChar)
            tvTopAnimeCharName.text = character.name
            tvTopAnimeCharRanking.text = "${position + 1}"
        }

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(character)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}