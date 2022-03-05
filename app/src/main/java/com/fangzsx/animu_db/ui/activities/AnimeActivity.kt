package com.fangzsx.animu_db.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TabHost
import com.fangzsx.animu_db.databinding.ActivityAnimeBinding

class AnimeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAnimeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimeBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}