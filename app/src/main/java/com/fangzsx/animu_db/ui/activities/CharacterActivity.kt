package com.fangzsx.animu_db.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.fangzsx.animu_db.databinding.ActivityCharacterBinding
import com.fangzsx.animu_db.viewmodels.anime.CharacterActivityViewModel

class CharacterActivity : AppCompatActivity() {
    private lateinit var binding : ActivityCharacterBinding
    private lateinit var characterVM : CharacterActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharacterBinding.inflate(layoutInflater)
        characterVM = CharacterActivityViewModel()
        setContentView(binding.root)

        val id = intent.getIntExtra("CHAR_ID", 0)
        characterVM.getCharacterByID(id)

        characterVM.character.observe(this){ charData ->

            binding.apply {
                clToolbar.title = charData.name

                Glide
                    .with(this@CharacterActivity)
                    .load(charData.images.jpg.image_url)
                    .into(ivCharImage)

                tvFullname.text = charData.name
                tvFullnameKanji.text = charData.name_kanji
                tvNicknames.text = charData.nicknames.toString()
                tvAbout.text = charData.about.trim()
            }

        }


    }
}