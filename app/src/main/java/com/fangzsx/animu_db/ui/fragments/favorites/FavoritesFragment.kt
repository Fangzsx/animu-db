package com.fangzsx.animu_db.ui.fragments.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fangzsx.animu_db.R
import com.fangzsx.animu_db.adapters.vp.AnimeViewPagerAdapter
import com.fangzsx.animu_db.adapters.vp.FavoritesViewPagerAdapter
import com.fangzsx.animu_db.databinding.FragmentFavoritesBinding
import com.google.android.material.tabs.TabLayoutMediator


class FavoritesFragment : Fragment() {
    private lateinit var binding : FragmentFavoritesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpTabLayout()

    }

    private fun setUpTabLayout() {
        val adapter = FavoritesViewPagerAdapter(requireParentFragment().parentFragmentManager, requireParentFragment().lifecycle)
        binding.vpFavorites.adapter = adapter


        TabLayoutMediator(binding.tlAnimeMangaFavorites, binding.vpFavorites) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "ANIME"
                }
                1 -> {
                    tab.text = "MANGA"
                }
            }
        }.attach()
    }

}