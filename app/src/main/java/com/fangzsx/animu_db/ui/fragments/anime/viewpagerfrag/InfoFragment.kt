package com.fangzsx.animu_db.ui.fragments.anime.viewpagerfrag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.fangzsx.animu_db.databinding.FragmentInfoBinding
import com.fangzsx.animu_db.viewmodels.anime.AnimeInfoFragmentViewModel
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView


class InfoFragment : Fragment(){

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


        //loading
        onLoadingState()

        animeInfoVM.getAnimeById(id)
        animeInfoVM.anime.observe(viewLifecycleOwner){ animeData ->
            onSuccessState()
            binding.apply {
                tvOriginalTitle.text = animeData.title
                tvEnglishTitle.text = animeData.title_english
                tvJapaneseTitle.text = animeData.title_japanese
            }

            val youtubeID = animeData.trailer.youtube_id
            youtubeID?.let{
                playTrailer(it)
            }
        }

    }

    private fun onSuccessState() {

        binding.apply {
            progressBar.visibility = View.INVISIBLE
            tvJapaneseTitle.visibility = View.VISIBLE
            tvJapaneseTitleLabel.visibility = View.VISIBLE
            tvEnglishTitle.visibility = View.VISIBLE
            tvEnglishTitleLabel.visibility = View.VISIBLE
            tvOriginalTitle.visibility = View.VISIBLE
            tvOriginalTitleLabel.visibility = View.VISIBLE
            tvEpisodesLabel.visibility = View.VISIBLE
            tvEpisodes.visibility = View.VISIBLE
            tvStatusLabel.visibility = View.VISIBLE
            tvStatus.visibility = View.VISIBLE
            tvAiredLabel.visibility = View.VISIBLE
            tvAired.visibility = View.VISIBLE
            tvRatingLabel.visibility = View.VISIBLE
            tvRating.visibility = View.VISIBLE
            tvScoreLabel.visibility = View.VISIBLE
            tvScore.visibility = View.VISIBLE
            tvSynopsisLabel.visibility = View.VISIBLE
            tvSynopsis.visibility = View.VISIBLE
        }

    }

    private fun onLoadingState() {
        binding.apply {
            progressBar.visibility = View.VISIBLE
            tvJapaneseTitle.visibility = View.INVISIBLE
            tvJapaneseTitleLabel.visibility = View.INVISIBLE
            tvEnglishTitle.visibility = View.INVISIBLE
            tvEnglishTitleLabel.visibility = View.INVISIBLE
            tvOriginalTitle.visibility = View.INVISIBLE
            tvOriginalTitleLabel.visibility = View.INVISIBLE
            tvEpisodesLabel.visibility = View.INVISIBLE
            tvEpisodes.visibility = View.INVISIBLE
            tvStatusLabel.visibility = View.INVISIBLE
            tvStatus.visibility = View.INVISIBLE
            tvAiredLabel.visibility = View.INVISIBLE
            tvAired.visibility = View.INVISIBLE
            tvRatingLabel.visibility = View.INVISIBLE
            tvRating.visibility = View.INVISIBLE
            tvScoreLabel.visibility = View.INVISIBLE
            tvScore.visibility = View.INVISIBLE
            tvSynopsisLabel.visibility = View.INVISIBLE
            tvSynopsis.visibility = View.INVISIBLE
        }
    }

    private fun playTrailer(youtubeID: String) {
        val youTubePlayerView: YouTubePlayerView = binding.vvTrailer
        lifecycle.addObserver(youTubePlayerView)

        youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                youTubePlayer.loadVideo(youtubeID, 0f)
            }
        })
    }

}