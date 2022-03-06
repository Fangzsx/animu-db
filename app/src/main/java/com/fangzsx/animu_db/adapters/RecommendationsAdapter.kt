package com.fangzsx.animu_db.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fangzsx.animu_db.databinding.RecommendationItemBinding
import com.fangzsx.animu_db.models.recommendation.Data
import kotlin.random.Random

class RecommendationsAdapter : RecyclerView.Adapter<RecommendationsAdapter.AnimeViewHolder>() {

    var onItemClick : ((Data) -> Unit)? = null

    inner class AnimeViewHolder(val binding : RecommendationItemBinding) : RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<Data>(){
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.entry == newItem.entry
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }

    }

    var differ = AsyncListDiffer(this, differCallback)


    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        val recommendation = differ.currentList[position]

        val random = Random.nextInt(0,1)

        val imageURL = recommendation.entry[random].images.jpg.image_url
        val title = recommendation.entry[random].title

        holder.binding.apply {
            Glide
                .with(holder.itemView)
                .load(imageURL)
                .into(ivRecommendedSrc)
            tvRecommendedTitle.text = title
        }

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(recommendation)
        }

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        return AnimeViewHolder(
            RecommendationItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


}