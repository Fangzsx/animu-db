package com.fangzsx.animu_db.ui.fragments.anime.viewpagerfrag

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fangzsx.animu_db.R
import com.fangzsx.animu_db.databinding.FragmentCharBinding

class CharFragment : Fragment() {

    private lateinit var binding : FragmentCharBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

}