package com.fangzsx.animu_db.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fangzsx.animu_db.databinding.ActivityMangaReviewBinding

class MangaReviewActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMangaReviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMangaReviewBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}