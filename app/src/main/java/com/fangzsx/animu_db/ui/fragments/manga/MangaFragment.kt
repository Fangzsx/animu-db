package com.fangzsx.animu_db.ui.fragments.manga

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fangzsx.animu_db.adapters.ImageSliderAdapter
import com.fangzsx.animu_db.adapters.MangaReviewAdapter
import com.fangzsx.animu_db.adapters.PopularMangaAdapter
import com.fangzsx.animu_db.databinding.FragmentMangaBinding
import com.fangzsx.animu_db.viewmodels.manga.MangaFragmentViewModel
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations

class MangaFragment : Fragment() {

    private lateinit var binding: FragmentMangaBinding
    private lateinit var mangaFragmentVM: MangaFragmentViewModel
    private lateinit var imageSliderAdapter : ImageSliderAdapter
    private lateinit var popularMangaAdapter : PopularMangaAdapter
    private lateinit var mangaRevieAdapter : MangaReviewAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mangaFragmentVM = ViewModelProvider(this).get(MangaFragmentViewModel::class.java)
        popularMangaAdapter = PopularMangaAdapter()
        mangaRevieAdapter = MangaReviewAdapter()
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
    }

    private fun setUpRecyclerViews() {
        mangaFragmentVM.recommendations.observe(viewLifecycleOwner) { list ->
            imageSliderAdapter.setList(list.subList(0, 10))
            setUpRecommendationImageSlider()
            successState()
        }


        mangaFragmentVM.topManga.observe(viewLifecycleOwner) { list ->
            popularMangaAdapter.differ.submitList(list)
            setUpPopularRecyclerView()
        }

        mangaFragmentVM.mangaReviews.observe(viewLifecycleOwner){ list ->
            mangaRevieAdapter.differ.submitList(list)
            setUpReviewRecyclerView()
        }



    }

    private fun setUpReviewRecyclerView() {
        binding.rvRecentReviewsManga.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            adapter = mangaRevieAdapter
        }
    }

    private fun setUpRecommendationImageSlider() {
        binding.isRecommendationsManga.setSliderAdapter(imageSliderAdapter)
        binding.isRecommendationsManga.setIndicatorAnimation(IndicatorAnimationType.WORM)
        binding.isRecommendationsManga.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
        binding.isRecommendationsManga.startAutoCycle()

        imageSliderAdapter.onItemClick = {
            Toast.makeText(activity, "${it.title}", Toast.LENGTH_SHORT).show()
        }

    }


    private fun successState() {
        binding.progressBar.visibility = View.INVISIBLE
    }

    private fun loadingState() {
        binding.progressBar.visibility = View.VISIBLE
    }



}