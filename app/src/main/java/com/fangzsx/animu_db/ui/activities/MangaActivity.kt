package com.fangzsx.animu_db.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.fangzsx.animu_db.databinding.ActivityMangaBinding
import com.fangzsx.animu_db.viewmodels.manga.MangaActivityViewModel
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


}