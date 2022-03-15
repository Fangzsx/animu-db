package com.fangzsx.animu_db.ui.fragments.anime

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.CursorAdapter
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.fangzsx.animu_db.R
import com.fangzsx.animu_db.adapters.AnimeReviewAdapter
import com.fangzsx.animu_db.adapters.PopularAnimeAdapter
import com.fangzsx.animu_db.adapters.RecommendationsAdapter
import com.fangzsx.animu_db.adapters.TopCharactersAdapter
import com.fangzsx.animu_db.databinding.FragmentAnimeBinding
import com.fangzsx.animu_db.ui.activities.AnimeActivity
import com.fangzsx.animu_db.ui.activities.CharacterActivity
import com.fangzsx.animu_db.ui.activities.ReviewActivity
import com.fangzsx.animu_db.ui.activities.SearchActivity
import com.fangzsx.animu_db.viewmodels.anime.AnimeFragmentViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


class AnimeFragment : Fragment() {
    private lateinit var animeFragmentVM : AnimeFragmentViewModel
    private lateinit var binding : FragmentAnimeBinding
    private lateinit var recommendationsAdapter : RecommendationsAdapter
    private lateinit var popularAdapter : PopularAnimeAdapter
    private lateinit var topCharacterAdapter : TopCharactersAdapter
    private lateinit var animeReviewAdapter : AnimeReviewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        animeFragmentVM = ViewModelProvider(this).get(AnimeFragmentViewModel::class.java)
        recommendationsAdapter = RecommendationsAdapter()
        popularAdapter = PopularAnimeAdapter()
        topCharacterAdapter = TopCharactersAdapter()
        animeReviewAdapter = AnimeReviewAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAnimeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeList()
        setupRecommendationsRecyclerView()
        setUpPopularRecyclerView()
        setUpAnimeReviewRecyclerView()
        loadTopCharactersAfter4Seconds()

        binding.ivSearch.setOnClickListener {
            Intent(activity, SearchActivity::class.java).also {
                startActivity(it)
            }
        }

    }



    private fun loadTopCharactersAfter4Seconds() {
        Handler().postDelayed({
            animeFragmentVM.getTopCharacters()
            animeFragmentVM.topCharacters.observe(requireParentFragment().viewLifecycleOwner) { list ->
                topCharacterAdapter.differ.submitList(list)
            }
            hideProgressBar()
            setUpTopCharactersRecyclerView()
        }, 4000)
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.INVISIBLE
    }

    private fun setupRecommendationsRecyclerView() {

        binding.rvRecommendations.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            adapter = recommendationsAdapter
        }

        recommendationsAdapter.onItemClick = { entry ->
            Intent(activity, AnimeActivity::class.java).apply {
                putExtra("MAL_ID", entry.mal_id)
                startActivity(this)
            }
        }
    }

    private fun setUpPopularRecyclerView(){
        binding.rvPopularNow.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            adapter = popularAdapter
        }

        popularAdapter.onItemClick = { data ->
            Intent(activity, AnimeActivity::class.java).apply {
                putExtra("MAL_ID", data.mal_id)
                startActivity(this)
            }
        }

    }

    private fun setUpTopCharactersRecyclerView(){

        binding.rvTopCharacters.apply{
            layoutManager = GridLayoutManager(activity, 3, GridLayoutManager.VERTICAL, false)
            adapter = topCharacterAdapter
        }
        topCharacterAdapter.onItemClick = { charData ->


            Intent(activity, CharacterActivity::class.java).apply {
                putExtra("CHAR_ID", charData.mal_id)
                startActivity(this)
            }
        }
    }

    private fun setUpAnimeReviewRecyclerView(){
        binding.rvReviews.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            adapter = animeReviewAdapter
        }

        animeReviewAdapter.onItemClick = {  review ->

            Intent(activity, ReviewActivity::class.java).apply {
                putExtra("IMAGE_URL", review.entry.images.jpg.image_url)
                putExtra("ANIME_REVIEW_TITLE", review.entry.title)
                putExtra("REVIEW_BY", review.user.username)
                putExtra("REVIEW_BY_IMAGE", review.user.images.jpg.image_url)
                putExtra("REVIEW_BY_URL",review.user.url)

                putExtra("REVIEW_OVERALL", review.scores.overall)
                putExtra("REVIEW_STORY",review.scores.story)
                putExtra("REVIEW_ANIMATION",review.scores.animation)
                putExtra("REVIEW_SOUND",review.scores.sound)
                putExtra("REVIEW_CHARACTER", review.scores.character)
                putExtra("REVIEW_ENJOYMENT",review.scores.enjoyment)
                putExtra("REVIEW_BODY", review.review)

                startActivity(this)
            }
        }
    }

    private fun observeList() {
        animeFragmentVM.animeRecommendations.observe(requireParentFragment().viewLifecycleOwner) { list ->
            recommendationsAdapter.differ.submitList(list)

        }
        animeFragmentVM.reviews.observe(requireParentFragment().viewLifecycleOwner){ list ->
            animeReviewAdapter.differ.submitList(list)
        }

        animeFragmentVM.animePopular.observe(requireParentFragment().viewLifecycleOwner){ list ->
            popularAdapter.differ.submitList(list)
        }

    }



}