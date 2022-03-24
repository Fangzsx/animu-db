package com.fangzsx.animu_db.ui.fragments.manga.viewpagerfrag

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.fangzsx.animu_db.R
import com.fangzsx.animu_db.adapters.MangaCharactersAdapter
import com.fangzsx.animu_db.databinding.FragmentMangaCharBinding
import com.fangzsx.animu_db.ui.activities.CharacterActivity
import com.fangzsx.animu_db.viewmodels.manga.MangaCharFragmentViewModel


class MangaCharFragment : Fragment() {
    private lateinit var binding : FragmentMangaCharBinding
    private lateinit var mangaCharVM : MangaCharFragmentViewModel
    private lateinit var charactersAdapter : MangaCharactersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mangaCharVM = ViewModelProvider(this).get(MangaCharFragmentViewModel::class.java)
        charactersAdapter = MangaCharactersAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMangaCharBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = requireActivity().intent.getIntExtra("MAL_ID", 0)


        Handler().postDelayed({
            mangaCharVM.getMangaCharactersByID(id)
            mangaCharVM.characters.observe(viewLifecycleOwner){ listOfCharacters ->

                if(listOfCharacters.isNotEmpty()){
                    charactersAdapter.differ.submitList(listOfCharacters)
                }else{
                    binding.tvNoResult.visibility = View.VISIBLE
                }
            }
            setUpCharactersRecyclerView()
        },500)



    }

    private fun setUpCharactersRecyclerView() {
        binding.rvCharactersManga.apply {
            layoutManager = GridLayoutManager(activity, 3, GridLayoutManager.VERTICAL, false)
            adapter = charactersAdapter
        }

        charactersAdapter.onItemClick = { charData ->
            Intent(activity, CharacterActivity::class.java).apply {
                putExtra("CHAR_ID", charData.mal_id)
                startActivity(this)
            }
        }
    }


}