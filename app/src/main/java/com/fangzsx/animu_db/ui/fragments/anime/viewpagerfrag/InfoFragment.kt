package com.fangzsx.animu_db.ui.fragments.anime.viewpagerfrag

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import androidx.lifecycle.ViewModelProvider
import com.fangzsx.animu_db.R
import com.fangzsx.animu_db.databinding.FragmentInfoBinding
import com.fangzsx.animu_db.ui.activities.AnimeActivity
import com.fangzsx.animu_db.viewmodels.anime.AnimeInfoFragmentViewModel

class InfoFragment : Fragment() {

    private lateinit var binding : FragmentInfoBinding
    private lateinit var animeInfoVM : AnimeInfoFragmentViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        animeInfoVM = ViewModelProvider(this).get(AnimeInfoFragmentViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInfoBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = requireActivity().intent.getIntExtra("MAL_ID", 0)
        animeInfoVM.getAnimeById(id)



        animeInfoVM.anime.observe(viewLifecycleOwner){ animeData ->
            binding.tvAnimeTitle.text = animeData.title
        }

    }

}