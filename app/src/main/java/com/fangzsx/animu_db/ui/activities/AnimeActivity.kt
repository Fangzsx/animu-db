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

    override fun onBackPressed() {
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        animeVM = ViewModelProvider(this).get(AnimeActivityViewModel::class.java)


        val animeIMG = intent.getStringExtra("ANIME_IMAGE_URL")
        val animeTitle = intent.getStringExtra("ANIME_TITLE")
        setAnimeImageAndTitle(animeIMG,animeTitle)

        setUpTabLayout()

    }

    private fun setAnimeImageAndTitle(url : String?, animeTitle: String?){
            Glide
                .with(this)
                .load(url)
                .into(binding.ivAnime)

            binding.clToolbar.title = animeTitle
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
                    tab.text = "MAIN CHARACTERS"
                }
            }
        }.attach()
    }
}