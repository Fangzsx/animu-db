package com.fangzsx.animu_db.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fangzsx.animu_db.databinding.AnimeReviewItemBinding
import com.fangzsx.animu_db.models.review.Data

class AnimeReviewAdapter : RecyclerView.Adapter<AnimeReviewAdapter.ReviewViewHolder>() {

    var onItemClick : ((Data) -> Unit)? = null

    inner class ReviewViewHolder(val binding : AnimeReviewItemBinding) : RecyclerView.ViewHolder(binding.root)

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
            AnimeReviewItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        val review = differ.currentList[position]

        holder.binding.apply {
            tvOverall.text = "${review.scores.overall}/10"
            tvStory.text = "${review.scores.story}/10"
            tvAnimation.text = "${review.scores.animation}/10"
            tvCharacter.text = "${review.scores.character}/10"
            tvEnjoyment.text = "${review.scores.enjoyment}/10"
            tvReviewBy.text = review.user.username
            tvSound.text = "${review.scores.sound}/10"
            tvReview.text = "\"${review.review}\""

            Glide
                .with(holder.itemView)
                .load(review.entry.images.jpg.image_url)
                .into(ivReviewSrc)
        }

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(review)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}