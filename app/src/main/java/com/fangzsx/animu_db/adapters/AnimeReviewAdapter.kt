package com.fangzsx.animu_db.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fangzsx.animu_db.databinding.AnimeReviewItemBinding
import com.fangzsx.animu_db.models.animereview.Data
import java.lang.StringBuilder

class AnimeReviewAdapter : RecyclerView.Adapter<AnimeReviewAdapter.ReviewViewHolder>() {

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
            tvOverall.text = "${review.scores.overall.toDouble()}/10.0"
            tvStory.text = "${review.scores.story.toDouble()}/10.0"
            tvAnimation.text = "${review.scores.animation.toDouble()}/10.0"
            tvCharacter.text = "${review.scores.character.toDouble()}/10.0"
            tvEnjoyment.text = "${review.scores.enjoyment.toDouble()}/10.0"
            tvReviewBy.text = review.user.username
            tvSound.text = "${review.scores.sound.toDouble()}/10.0"
            tvReview.text = "\"${review.review}\" "


            Glide
                .with(holder.itemView)
                .load(review.entry.images.jpg.image_url)
                .into(ivReviewAnime)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}