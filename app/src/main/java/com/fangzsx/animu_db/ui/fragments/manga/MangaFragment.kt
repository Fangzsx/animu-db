package com.fangzsx.animu_db.ui.fragments.manga

import android.content.Intent
import android.os.Bundle
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
        binding = FragmentMangaBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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

        mangaReviewAdapter.onItemClick = {
            Intent()
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