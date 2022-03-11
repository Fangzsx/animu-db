package com.fangzsx.animu_db.ui.fragments.anime.viewpagerfrag

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        animeCharVM = AnimeCharacterFragmentViewModel()
        charactersAdapter = AnimeCharactersAdapter()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = requireActivity().intent.getIntExtra("MAL_ID", 0)

        animeCharVM.getCharactersByAnimeID(id)
        animeCharVM.characters.observe(viewLifecycleOwner){ characterList ->
            charactersAdapter.differ.submitList(characterList)
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

}