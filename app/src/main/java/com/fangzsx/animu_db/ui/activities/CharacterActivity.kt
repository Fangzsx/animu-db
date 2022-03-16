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
        val id = intent.getIntExtra("CHAR_ID", 0)

        characterVM.getCharacterByID(id)

        characterVM.character.observe(this){ charData ->

            charData?.let { data ->

                binding.apply {
                    clToolbar.title = data.name
                    Glide
                        .with(this@CharacterActivity)
                        .load(data.images.jpg.image_url)
                        .into(ivCharImage)

                    tvFullname.text = data.name
                    tvFullnameKanji.text = data.name_kanji
                    tvNicknames.text = data.nicknames.toString()
                    if(data.about == null){
                        tvAbout.text = "No Information"
                    }else{
                        tvAbout.text = data.about
                    }

                }
                successState()
            }
        }


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