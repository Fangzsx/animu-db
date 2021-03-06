package com.fangzsx.animu_db.ui.fragments.anime.viewpagerfrag

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.fangzsx.animu_db.R
import com.fangzsx.animu_db.adapters.AnimeCharactersAdapter
import com.fangzsx.animu_db.databinding.FragmentCharBinding
import com.fangzsx.animu_db.ui.activities.CharacterActivity
import com.fangzsx.animu_db.viewmodels.anime.AnimeCharacterFragmentViewModel

class CharFragment : Fragment() {

    private lateinit var binding : FragmentCharBinding
    private lateinit var animeCharVM : AnimeCharacterFragmentViewModel
    private lateinit var charactersAdapter : AnimeCharactersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        animeCharVM = ViewModelProvider(this).get(AnimeCharacterFragmentViewModel::class.java)
        charactersAdapter = AnimeCharactersAdapter()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = requireActivity().intent.getIntExtra("MAL_ID", 0)

        animeCharVM.getCharactersByAnimeID(id)
        animeCharVM.characters.observeOnce(viewLifecycleOwner){ characterList ->

            if(characterList.isNotEmpty()){
                charactersAdapter.differ.submitList(characterList)
            }else{
                binding.tvNoResult.visibility = View.VISIBLE
            }

        }

        binding.rvCharacters.apply{
            layoutManager = GridLayoutManager(activity, 3, GridLayoutManager.VERTICAL, false)
            adapter = charactersAdapter
        }

        charactersAdapter.onItemClick = { character ->
            Intent(activity, CharacterActivity::class.java).apply{
                putExtra("CHAR_ID", character.mal_id)
                startActivity(this)
            }
        }
    }
    private fun <T> LiveData<T>.observeOnce(lifecycleOwner: LifecycleOwner, observer: Observer<T>) {
        observe(lifecycleOwner, object : Observer<T> {
            override fun onChanged(t: T?) {
                observer.onChanged(t)
                removeObserver(this)
            }
        })
    }

}