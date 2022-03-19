package com.fangzsx.animu_db.ui.fragments.manga

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.fangzsx.animu_db.R
import com.fangzsx.animu_db.databinding.FragmentMangaInfoBinding
import com.fangzsx.animu_db.viewmodels.anime.MangaInfoFragmentViewModel
import java.lang.StringBuilder


class MangaInfoFragment : Fragment() {

    private lateinit var binding : FragmentMangaInfoBinding
    private lateinit var mangaInfoVM : MangaInfoFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mangaInfoVM = ViewModelProvider(this).get(MangaInfoFragmentViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMangaInfoBinding.inflate(layoutInflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = requireActivity().intent.getIntExtra("MAL_ID", 0)

        loadingState()
        mangaInfoVM.getMangaByID(id)
        mangaInfoVM.manga.observe(viewLifecycleOwner){ mangaData ->

            binding.apply {
                tvOriginalTitleManga.text = mangaData.title
                tvEnglishTitleManga.text = mangaData.title_english
                tvJapaneseTitleManga.text = mangaData.title_japanese
                tvChaptersManga.text = mangaData.chapters.toString()
                tvStatusManga.text = mangaData.status

                val sb = StringBuilder()
                for(author in mangaData.authors){
                    sb.append("[${author.name}] ")
                }

                tvAuthorsManga.text = sb.toString()
                tvPublishedManga.text = mangaData.published.string
                tvMangaScore.text = "Score: ${mangaData.scored}/10"
                tvMangaScoredBy.text = "Scored by: ${mangaData.scored_by} users"
                tvMangaPopularity.text = "Popularity: ${mangaData.popularity}"
                tvMangaRanking.text = "Rank: ${mangaData.rank}"
                tvSynopsisManga.text = mangaData.synopsis
            }
            successState()
        }
    }

    private fun loadingState() {

        binding.apply {
            progressBar.visibility = View.VISIBLE
            tvOriginalTitleMangaLabel.visibility = View.INVISIBLE
            tvEnglishTitleMangaLabel.visibility = View.INVISIBLE
            tvJapaneseTitleMangaLabel.visibility = View.INVISIBLE
            tvChaptersMangaLabel.visibility = View.INVISIBLE
            tvStatusMangaLabel.visibility = View.INVISIBLE
            tvAuthorsMangaLabel.visibility = View.INVISIBLE
            tvPublishedMangaLabel.visibility = View.INVISIBLE
            tvRatingMangaLabel.visibility = View.INVISIBLE
            tvMangaScore.visibility = View.INVISIBLE
            tvMangaScoredBy.visibility = View.INVISIBLE
            tvMangaPopularity.visibility = View.INVISIBLE
            tvMangaRanking.visibility = View.INVISIBLE
            tvSynopsisMangaLabel.visibility = View.INVISIBLE

        }

    }


    private fun successState() {
        binding.apply {
            progressBar.visibility = View.INVISIBLE
            tvOriginalTitleMangaLabel.visibility = View.VISIBLE
            tvEnglishTitleMangaLabel.visibility = View.VISIBLE
            tvJapaneseTitleMangaLabel.visibility = View.VISIBLE
            tvChaptersMangaLabel.visibility = View.VISIBLE
            tvStatusMangaLabel.visibility = View.VISIBLE
            tvAuthorsMangaLabel.visibility = View.VISIBLE
            tvPublishedMangaLabel.visibility = View.VISIBLE
            tvRatingMangaLabel.visibility = View.VISIBLE
            tvMangaScore.visibility = View.VISIBLE
            tvMangaScoredBy.visibility = View.VISIBLE
            tvMangaPopularity.visibility = View.VISIBLE
            tvMangaRanking.visibility = View.VISIBLE
            tvSynopsisMangaLabel.visibility = View.VISIBLE

        }
    }

}