package com.fangzsx.animu_db.ui.fragments.anime.viewpagerfrag

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fangzsx.animu_db.R
import com.fangzsx.animu_db.databinding.FragmentInfoBinding
import com.fangzsx.animu_db.ui.activities.AnimeActivity

class InfoFragment : Fragment() {

    private lateinit var binding : FragmentInfoBinding

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
        binding.tvId.text = id.toString()
    }

}