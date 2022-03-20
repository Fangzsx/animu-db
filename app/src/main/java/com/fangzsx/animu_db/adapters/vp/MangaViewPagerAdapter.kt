package com.fangzsx.animu_db.adapters.vp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.fangzsx.animu_db.ui.fragments.manga.viewpagerfrag.MangaCharFragment
import com.fangzsx.animu_db.ui.fragments.manga.viewpagerfrag.MangaInfoFragment

class MangaViewPagerAdapter(fragmentManager: FragmentManager, lifecycle : Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> {
                MangaInfoFragment()
            }
            1 -> {
                MangaCharFragment()
            }
            else -> Fragment()
        }
    }
}