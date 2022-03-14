package com.fangzsx.animu_db.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fangzsx.animu_db.databinding.ActivityReviewBinding

class ReviewActivity : AppCompatActivity() {
    private lateinit var binding : ActivityReviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        

    }
}