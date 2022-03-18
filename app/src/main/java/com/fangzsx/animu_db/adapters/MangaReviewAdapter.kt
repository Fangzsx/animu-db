package com.fangzsx.animu_db.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fangzsx.animu_db.databinding.MangaReviewItemBinding
import com.fangzsx.animu_db.models.review.Data

class MangaReviewAdapter : RecyclerView.Adapter<MangaReviewAdapter.ReviewViewHolder>() {

    inner class ReviewViewHolder(val binding : MangaReviewItemBinding) : RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<Data>(){
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.mal_id == newItem.mal_id
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }
    }

    var differ = AsyncListDiffer(this, differCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        return ReviewViewHolder(
            MangaReviewItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        val reviewItem = differ.currentList[position]

        holder.binding.apply {
            Glide
                .with(holder.itemView)
                .load(reviewItem.entry.images.jpg.image_url)
                .into(ivReviewMangaSrc)

            tvReviewMangaBy.text = reviewItem.user.username
            tvCharacterManga.text = "${reviewItem.scores.character}/10"
            tvEnjoymentManga.text =  "${reviewItem.scores.enjoyment}/10"
            tvOverallManga.text =  "${reviewItem.scores.overall}/10"
            tvStoryManga.text =  "${reviewItem.scores.story}/10"
            tvMangaArt.text = "${reviewItem.scores.art}/10"
            tvReviewManga.text = "\"${reviewItem.review}\""
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


}