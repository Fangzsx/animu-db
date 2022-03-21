package com.fangzsx.animu_db.ui.fragments.manga.viewpagerfrag

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fangzsx.animu_db.R
import com.fangzsx.animu_db.databinding.FragmentMangaCharBinding


class MangaCharFragment : Fragment() {
    private lateinit var binding : FragmentMangaCharBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMangaCharBinding.inflate(inflater, container, false)
        return inflater.inflate(R.layout.fragment_manga_char, container, false)
    }


}