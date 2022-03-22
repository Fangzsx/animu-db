package com.fangzsx.animu_db.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fangzsx.animu_db.databinding.MangaSearchResultItemBinding
import com.fangzsx.animu_db.models.searchManga.Data

class SearchMangaResultsAdapter : RecyclerView.Adapter<SearchMangaResultsAdapter.MangaResultViewHolder>() {

    var onItemClick : ((Data) -> Unit)? = null
    inner class MangaResultViewHolder(val binding : MangaSearchResultItemBinding) : RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<Data>(){
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.mal_id == newItem.mal_id
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }

    }

    var differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MangaResultViewHolder {
        return MangaResultViewHolder(
            MangaSearchResultItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MangaResultViewHolder, position: Int) {
        val manga = differ.currentList[position]
        holder.binding.apply {
            Glide
                .with(holder.itemView)
                .load(manga.images.jpg.image_url)
                .into(ivSearchMangaSrc)

            tvSearchMangaTitle.text = manga.title
        }

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(manga)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}