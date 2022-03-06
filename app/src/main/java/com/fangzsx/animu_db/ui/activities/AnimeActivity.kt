package com.fangzsx.animu_db.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TabHost
import com.fangzsx.animu_db.adapters.vp.AnimeViewPagerAdapter
import com.fangzsx.animu_db.databinding.ActivityAnimeBinding
import com.google.android.material.tabs.TabLayoutMediator

class AnimeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAnimeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpTabLayout()

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