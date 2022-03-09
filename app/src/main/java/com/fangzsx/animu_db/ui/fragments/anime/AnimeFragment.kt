package com.fangzsx.animu_db.ui.fragments.anime

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fangzsx.animu_db.adapters.PopularAnimeAdapter
import com.fangzsx.animu_db.adapters.RecommendationsAdapter
import com.fangzsx.animu_db.databinding.FragmentAnimeBinding
import com.fangzsx.animu_db.ui.activities.AnimeActivity
import com.fangzsx.animu_db.viewmodels.anime.AnimeFragmentViewModel


class AnimeFragment : Fragment() {
    private lateinit var animeFragmentVM : AnimeFragmentViewModel
    private lateinit var binding : FragmentAnimeBinding
    private lateinit var recommendationsAdapter : RecommendationsAdapter
    private lateinit var popularAdapter : PopularAnimeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        animeFragmentVM = ViewModelProvider(this).get(AnimeFragmentViewModel::class.java)
        recommendationsAdapter = RecommendationsAdapter()
        popularAdapter = PopularAnimeAdapter()
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

    private fun observeList() {
        animeFragmentVM.animeRecommendations.observe(viewLifecycleOwner) { list ->
            recommendationsAdapter.differ.submitList(list)

        }

        animeFragmentVM.animePopular.observe(viewLifecycleOwner){ list ->
            popularAdapter.differ.submitList(list)
        }
    }


}