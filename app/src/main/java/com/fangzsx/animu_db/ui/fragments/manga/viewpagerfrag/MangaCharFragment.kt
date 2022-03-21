package com.fangzsx.animu_db.ui.fragments.manga.viewpagerfrag

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.fangzsx.animu_db.R
import com.fangzsx.animu_db.adapters.MangaCharactersAdapter
import com.fangzsx.animu_db.databinding.FragmentMangaCharBinding
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


        mangaCharVM.getMangaCharactersByID(id)
        mangaCharVM.characters.observe(viewLifecycleOwner){ listOfCharacters ->
            charactersAdapter.differ.submitList(listOfCharacters)

        }

        binding.rvCharactersManga.apply {
            layoutManager = GridLayoutManager(activity, 3, GridLayoutManager.VERTICAL, false)
            adapter = charactersAdapter
        }



    }


}