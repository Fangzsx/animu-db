package com.fangzsx.animu_db.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.Glide
import com.fangzsx.animu_db.databinding.RecommendationMangaItemBinding
import com.fangzsx.animu_db.models.recommendation.Data
import com.fangzsx.animu_db.models.recommendation.Entry
import com.smarteist.autoimageslider.SliderViewAdapter

class ImageSliderAdapter() : SliderViewAdapter<ImageSliderAdapter.ImageViewHolder>() {

    inner class ImageViewHolder(val binding : RecommendationMangaItemBinding) : SliderViewAdapter.ViewHolder(binding.root)

    private var list : List<Data> = mutableListOf()
    var onItemClick : ((Entry) -> Unit)? = null


    fun setList(data : List<Data>){
        list = data
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return list.size
    }


    override fun onCreateViewHolder(parent: ViewGroup?): ImageViewHolder {
        return ImageViewHolder(
            RecommendationMangaItemBinding.inflate(
                LayoutInflater.from(parent?.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(viewHolder: ImageViewHolder?, position: Int) {
        val recommended = list[position].entry[0]

        viewHolder?.binding?.apply {
            Glide
                .with(viewHolder.itemView)
                .load(recommended.images.jpg.large_image_url)
                .into(ivRecommendedSrc)
        }


        viewHolder!!.itemView.setOnClickListener {
            onItemClick?.invoke(recommended)
        }
    }


}