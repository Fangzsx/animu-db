package com.fangzsx.animu_db.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fangzsx.animu_db.R
import com.fangzsx.animu_db.databinding.PopularMangaItemBinding
import com.fangzsx.animu_db.models.topmanga.Data

class PopularMangaAdapter : RecyclerView.Adapter<PopularMangaAdapter.MangaViewHolder>() {

    var onItemClick : ((Data) -> Unit)? = null

    inner class MangaViewHolder(val binding : PopularMangaItemBinding) : RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<Data>(){
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.mal_id == newItem.mal_id
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }
    }

    var differ = AsyncListDiffer(this, differCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MangaViewHolder {
        return MangaViewHolder(
            PopularMangaItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MangaViewHolder, position: Int) {
        val manga = differ.currentList[position]

        when(position){
            0 -> holder.binding.tvPopularRank.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.gold))
            1 -> holder.binding.tvPopularRank.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.silver))
            2 -> holder.binding.tvPopularRank.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.bronze))
            else -> holder.binding.tvPopularRank.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.gray))


        }

        holder.binding.apply {
            Glide
                .with(holder.itemView)
                .load(manga.images.jpg.image_url)
                .into(ivPopularMangaSrc)

            tvPopularMangaTitle.text = manga.title
            tvPopularRank.text = "${position + 1}"
        }

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(manga)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


}