package com.fangzsx.animu_db.adapters.vp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.fangzsx.animu_db.ui.fragments.anime.viewpagerfrag.CharFragment
import com.fangzsx.animu_db.ui.fragments.anime.viewpagerfrag.InfoFragment

class AnimeViewPagerAdapter(fragmentManager: FragmentManager, lifecycle : Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> {
                InfoFragment()
            }
            1 -> {
                CharFragment()
            }

            else -> Fragment()
        }
    }
}