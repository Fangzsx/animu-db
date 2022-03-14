package com.fangzsx.animu_db.ui.activities

import android.content.Intent
import android.graphics.Paint
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.fangzsx.animu_db.databinding.ActivityReviewBinding


class ReviewActivity : AppCompatActivity() {
    private lateinit var binding : ActivityReviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user = intent.getStringExtra("REVIEW_BY")
        val userImage = intent.getStringExtra("REVIEW_BY_IMAGE")
        val userURL = intent.getStringExtra("REVIEW_BY_URL")

        val title = intent.getStringExtra("ANIME_REVIEW_TITLE")
        val overallScore = intent.getIntExtra("REVIEW_OVERALL",0)
        val storyScore = intent.getIntExtra("REVIEW_STORY",0)
        val animationScore = intent.getIntExtra("REVIEW_ANIMATION",0)
        val soundScore = intent.getIntExtra("REVIEW_SOUND",0)
        val charScore = intent.getIntExtra("REVIEW_CHARACTER",0)
        val enjoymentScore = intent.getIntExtra("REVIEW_ENJOYMENT",0)
        val reviewBody = intent.getStringExtra("REVIEW_BODY")
        val image = intent.getStringExtra("IMAGE_URL")


        binding.apply {
            Glide
                .with(this@ReviewActivity)
                .load(image)
                .into(ivReviewSrc)

            //set review score fields
            tvReviewTitle.text = title
            tvOverall.text = "$overallScore/10"
            tvStory.text = "$storyScore/10"
            tvAnimation.text = "$animationScore/10"
            tvCharacter.text = "$charScore/10"
            tvEnjoyment.text = "$enjoymentScore/10"
            tvSound.text = "$soundScore/10"
            tvReview.text = "\"$reviewBody\""

            //reviewer data
            Glide
                .with(this@ReviewActivity)
                .load(userImage)
                .into(ivUser)
            tvUsername.text = user
            tvProfileUrl.paintFlags = Paint.UNDERLINE_TEXT_FLAG
            tvProfileUrl.text = userURL

            //set intent to view reviewer's data
            tvProfileUrl.setOnClickListener {
                goToProfileURL(userURL)
            }
        }

    }

    private fun goToProfileURL(userURL: String?) {
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(userURL)
        startActivity(i)
    }
}