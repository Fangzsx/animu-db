package com.fangzsx.animu_db.ui.fragments.anime.viewpagerfrag

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.fangzsx.animu_db.databinding.FragmentInfoBinding
import com.fangzsx.animu_db.models.anime.Data
import com.fangzsx.animu_db.viewmodels.anime.AnimeInfoFragmentViewModel
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerCallback
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.YouTubePlayerTracker
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.loadOrCueVideo
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import java.util.*


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

        val origTitle = requireActivity().intent.getStringExtra("ANIME_TITLE")
        val engTitle = requireActivity().intent.getStringExtra("ANIME_TITLE_ENG")
        val japTitle = requireActivity().intent.getStringExtra("ANIME_TITLE_JAP")
        val episodes = requireActivity().intent.getIntExtra("ANIME_EPISODES",0)
        val status = requireActivity().intent.getStringExtra("ANIME_STATUS")
        val aired = requireActivity().intent.getStringExtra("ANIME_AIRED")
        val rating = requireActivity().intent.getStringExtra("ANIME_RATING")
        val score = requireActivity().intent.getDoubleExtra("ANIME_SCORE",0.0)
        val synopsis = requireActivity().intent.getStringExtra("ANIME_SYNOPSIS")
        val youtubeID = requireActivity().intent.getStringExtra("ANIME_TRAILER_YT_ID")

        binding.apply {
            tvOriginalTitle.text = origTitle
            tvEnglishTitle.text = engTitle
            tvJapaneseTitle.text = japTitle
            tvEpisodes.text = episodes.toString()
            tvStatus.text = status
            tvAired.text = aired
            tvRating.text = rating
            tvScore.text = score.toString()
            tvSynopsis.text = synopsis

            playTrailer(youtubeID)
        }
    }




    private fun playTrailer(youtubeID: String?) {
        val youTubePlayerView: YouTubePlayerView = binding.vvTrailer
        lifecycle.addObserver(youTubePlayerView)

        if(youtubeID != null){
            youTubePlayerView.getYouTubePlayerWhenReady(object : YouTubePlayerCallback{
                override fun onYouTubePlayer(youTubePlayer: YouTubePlayer) {

                    youtubeID?.let {
                        youTubePlayer.loadOrCueVideo(lifecycle, youtubeID, 0f)
                    }
                }
            })
        }else{
            Toast.makeText(activity, "Trailer not available.", Toast.LENGTH_SHORT).show()
        }
    }

}