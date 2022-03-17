package com.fangzsx.animu_db.ui.fragments.manga

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.fangzsx.animu_db.databinding.FragmentMangaBinding
import com.fangzsx.animu_db.viewmodels.manga.MangaFragmentViewModel

class MangaFragment : Fragment() {

    private lateinit var binding: FragmentMangaBinding
    private lateinit var mangaFragmentVM: MangaFragmentViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mangaFragmentVM = ViewModelProvider(this).get(MangaFragmentViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMangaBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        mangaFragmentVM.getMangaRecommendation()
        mangaFragmentVM.recommendations.observe(viewLifecycleOwner) { list ->
            Log.i("asd", list.size.toString())
            for(item in list){
                Log.i("test", item.entry[0].title.toString())
            }

        }


    }



}