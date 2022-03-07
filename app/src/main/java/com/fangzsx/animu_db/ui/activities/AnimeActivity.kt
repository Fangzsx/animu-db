package com.fangzsx.animu_db.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.fangzsx.animu_db.R
import com.fangzsx.animu_db.adapters.vp.AnimeViewPagerAdapter
import com.fangzsx.animu_db.databinding.ActivityAnimeBinding
import com.fangzsx.animu_db.viewmodels.anime.AnimeActivityViewModel
import com.google.android.material.tabs.TabLayoutMediator

class AnimeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAnimeBinding
    private lateinit var animeVM : AnimeActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        animeVM = ViewModelProvider(this).get(AnimeActivityViewModel::class.java)

        val animeID = intent.getIntExtra("MAL_ID", 0)
        setAnimeImage(animeID)

        setUpTabLayout()

    }

    private fun setAnimeImage(id : Int){
        animeVM.getAnimeById(id)
        animeVM.anime.observe(this){ animeData ->
            Glide
                .with(this)
                .load(animeData.images.webp.large_image_url)
                .into(binding.ivAnime)

            binding.clToolbar.title = animeData.title
        }

    }

    private fun setUpTabLayout() {
        val adapter = AnimeViewPagerAdapter(supportFragmentManager, lifecycle)
        binding.vpAnime.adapter = adapter


        TabLayoutMediator(binding.tlInfoChars, binding.vpAnime) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "INFO"
                }
                1 -> {
                    tab.text = "CHARACTERS"
                }
            }
        }.attach()
    }
}