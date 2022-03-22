package com.fangzsx.animu_db.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.fangzsx.animu_db.adapters.vp.MangaViewPagerAdapter
import com.fangzsx.animu_db.databinding.ActivityMangaBinding
import com.fangzsx.animu_db.viewmodels.manga.MangaActivityViewModel
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.Job

class MangaActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMangaBinding
    private lateinit var mangaActivityVM : MangaActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMangaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mangaActivityVM = ViewModelProvider(this).get(MangaActivityViewModel::class.java)


        val id = intent.getIntExtra("MAL_ID", 0)
        setUpTabLayout()
        mangaActivityVM.getMangaByID(id)

        mangaActivityVM.manga.observe(this){ data ->
            setImage(data.images.jpg.large_image_url)
            binding.clToolbar.title = data.title
        }


    }

    private fun setImage(imageURL: String) {
        Glide
            .with(this@MangaActivity)
            .load(imageURL)
            .into(binding.ivManga)
    }

    private fun setUpTabLayout(){
        val adapter = MangaViewPagerAdapter(supportFragmentManager, lifecycle)
        binding.vpManga.adapter = adapter

        TabLayoutMediator(binding.tlInfoChars, binding.vpManga){ tab, position ->
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