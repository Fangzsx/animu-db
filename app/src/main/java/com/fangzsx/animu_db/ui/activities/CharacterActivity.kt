package com.fangzsx.animu_db.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.fangzsx.animu_db.databinding.ActivityCharacterBinding
import com.fangzsx.animu_db.viewmodels.anime.CharacterActivityViewModel

class CharacterActivity : AppCompatActivity() {
    private lateinit var binding : ActivityCharacterBinding
    private lateinit var characterVM : CharacterActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharacterBinding.inflate(layoutInflater)
        characterVM = ViewModelProvider(this).get(CharacterActivityViewModel::class.java)
        setContentView(binding.root)


        loadingState()
        val fullname = intent.getStringExtra("CHAR_FULL_NAME")
        val kanjiname = intent.getStringExtra("CHAR_KANJI_NAME")
        val nicknames = intent.getStringExtra("CHAR_NICK_NAMES")
        val about = intent.getStringExtra("CHAR_ABOUT")
        val imageURL = intent.getStringExtra("CHAR_IMAGE_URL")

        binding.apply {
            tvFullname.text = fullname
            tvFullnameKanji.text = kanjiname
            tvNicknames.text = nicknames
            tvAbout.text = about

            Glide
                .with(this@CharacterActivity)
                .load(imageURL)
                .into(ivCharImage)
        }
        successState()



    }

    private fun successState() {

        binding.apply {
            progressBar.visibility = View.INVISIBLE
            tvFullname.visibility = View.VISIBLE
            tvFullnameLabel.visibility = View.VISIBLE
            tvFullnameKanjiLabel.visibility = View.VISIBLE
            tvFullnameKanji.visibility = View.VISIBLE
            tvNicknameLabel.visibility = View.VISIBLE
            tvNicknames.visibility = View.VISIBLE
            tvAboutLabel.visibility = View.VISIBLE
            tvAbout.visibility = View.VISIBLE
        }

    }

    private fun loadingState() {
        binding.apply {
            progressBar.visibility = View.VISIBLE
            tvFullname.visibility = View.INVISIBLE
            tvFullnameLabel.visibility = View.INVISIBLE
            tvFullnameKanjiLabel.visibility = View.INVISIBLE
            tvFullnameKanji.visibility = View.INVISIBLE
            tvNicknameLabel.visibility = View.INVISIBLE
            tvNicknames.visibility = View.INVISIBLE
            tvAboutLabel.visibility = View.INVISIBLE
            tvAbout.visibility = View.INVISIBLE
        }
    }
}