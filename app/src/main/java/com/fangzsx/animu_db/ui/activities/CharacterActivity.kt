package com.fangzsx.animu_db.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.fangzsx.animu_db.databinding.ActivityCharacterBinding
import com.fangzsx.animu_db.viewmodels.anime.CharacterActivityViewModel

class CharacterActivity : AppCompatActivity() {
    private lateinit var binding : ActivityCharacterBinding
    private lateinit var characterVM : CharacterActivityViewModel

    override fun onBackPressed() {
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharacterBinding.inflate(layoutInflater)
        characterVM = ViewModelProvider(this).get(CharacterActivityViewModel::class.java)
        setContentView(binding.root)


        loadingState()

        Handler().postDelayed({
            val charID = intent.getIntExtra("CHAR_ID", 0)

            characterVM.getCharacterByID(charID)
            characterVM.character.observeOnce(this) { charData ->

                charData?.let {
                    binding.apply {

                        clToolbar.title = charData.name

                        Glide
                            .with(this@CharacterActivity)
                            .load(charData.images.jpg.image_url)
                            .into(ivCharImage)
                        tvFullname.text = charData.name
                        tvFullnameKanji.text = charData.name_kanji
                        tvNicknames.text = charData.nicknames.toString()
                        tvAbout.text = charData.about

                    }
                    successState()
                }
            }
        },500)


    }
    private fun <T> LiveData<T>.observeOnce(lifecycleOwner: LifecycleOwner, observer: Observer<T>) {
        observe(lifecycleOwner, object : Observer<T> {
            override fun onChanged(t: T?) {
                observer.onChanged(t)
                removeObserver(this)
            }
        })
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