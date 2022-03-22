package com.fangzsx.animu_db.ui.fragments.manga

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fangzsx.animu_db.adapters.ImageSliderAdapter
import com.fangzsx.animu_db.adapters.MangaReviewAdapter
import com.fangzsx.animu_db.adapters.PopularMangaAdapter
import com.fangzsx.animu_db.databinding.FragmentMangaBinding
import com.fangzsx.animu_db.ui.activities.MangaActivity
import com.fangzsx.animu_db.ui.activities.MangaReviewActivity
import com.fangzsx.animu_db.ui.activities.SearchAnimeActivity
import com.fangzsx.animu_db.ui.activities.SearchMangaActivity
import com.fangzsx.animu_db.viewmodels.manga.MangaFragmentViewModel
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations

class MangaFragment : Fragment() {

    private lateinit var binding: FragmentMangaBinding
    private lateinit var mangaFragmentVM: MangaFragmentViewModel
    private lateinit var imageSliderAdapter : ImageSliderAdapter
    private lateinit var popularMangaAdapter : PopularMangaAdapter
    private lateinit var mangaReviewAdapter : MangaReviewAdapter




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mangaFragmentVM = ViewModelProvider(this).get(MangaFragmentViewModel::class.java)
        popularMangaAdapter = PopularMangaAdapter()
        mangaReviewAdapter = MangaReviewAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMangaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Handler().postDelayed({
            mangaFragmentVM.getMangaReviews()
            mangaFragmentVM.getTopManga()
            mangaFragmentVM.getMangaRecommendation()
        },250)


        binding.ivSearch.setOnClickListener {
            Intent(activity, SearchMangaActivity::class.java).also {
                startActivity(it)
            }
        }


        imageSliderAdapter = ImageSliderAdapter()
        loadingState()
        setUpRecyclerViews()

    }

    private fun setUpPopularRecyclerView() {
        binding.rvPopularMangaNow.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            adapter = popularMangaAdapter
        }

        popularMangaAdapter.onItemClick = { mangaData ->
            Intent(activity, MangaActivity::class.java).apply {
                putExtra("MAL_ID", mangaData.mal_id)
                startActivity(this)
            }
        }
    }

    private fun setUpRecyclerViews() {
        mangaFragmentVM.recommendations.observeOnce(viewLifecycleOwner) { list ->
            imageSliderAdapter.setList(list.subList(0, 10))
            setUpRecommendationImageSlider()
            successState()
        }

        mangaFragmentVM.topManga.observeOnce(viewLifecycleOwner) { list ->
            popularMangaAdapter.differ.submitList(list)
            setUpPopularRecyclerView()
        }

        mangaFragmentVM.mangaReviews.observeOnce(viewLifecycleOwner){ list ->
            mangaReviewAdapter.differ.submitList(list)
            setUpReviewRecyclerView()
            reviewsSuccessState()
        }


    }

    private fun setUpReviewRecyclerView() {
        binding.rvRecentReviewsManga.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            adapter = mangaReviewAdapter
        }

        mangaReviewAdapter.onItemClick = { review ->
            Intent(activity, MangaReviewActivity::class.java).apply {
                putExtra("IMAGE_URL", review.entry.images.jpg.image_url)
                putExtra("ANIME_REVIEW_TITLE", review.entry.title)
                putExtra("REVIEW_BY", review.user.username)
                putExtra("REVIEW_BY_IMAGE", review.user.images.jpg.image_url)
                putExtra("REVIEW_BY_URL",review.user.url)

                putExtra("REVIEW_OVERALL", review.scores.overall)
                putExtra("REVIEW_STORY",review.scores.story)
                putExtra("REVIEW_CHARACTER", review.scores.character)
                putExtra("REVIEW_ENJOYMENT",review.scores.enjoyment)
                putExtra("REVIEW_BODY", review.review)
                startActivity(this)
            }
        }
    }

    private fun setUpRecommendationImageSlider() {
        binding.isRecommendationsManga.setSliderAdapter(imageSliderAdapter)
        binding.isRecommendationsManga.setIndicatorAnimation(IndicatorAnimationType.WORM)
        binding.isRecommendationsManga.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
        binding.isRecommendationsManga.startAutoCycle()

        imageSliderAdapter.onItemClick = { entry ->
            Intent(activity, MangaActivity::class.java).apply {
                putExtra("MAL_ID", entry.mal_id)
                startActivity(this)
            }
        }

    }

    private fun reviewsSuccessState(){
        binding.pgRecentReviews.visibility = View.INVISIBLE
    }

    private fun successState() {
        binding.progressBar.visibility = View.INVISIBLE
    }

    private fun loadingState() {
        binding.progressBar.visibility = View.VISIBLE
        binding.pgRecentReviews.visibility = View.VISIBLE
    }


    private fun <T> LiveData<T>.observeOnce(lifecycleOwner: LifecycleOwner, observer: Observer<T>) {
        observe(lifecycleOwner, object : Observer<T> {
            override fun onChanged(t: T?) {
                observer.onChanged(t)
                removeObserver(this)
            }
        })
    }

}