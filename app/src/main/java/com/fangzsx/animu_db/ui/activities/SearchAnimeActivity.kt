package com.fangzsx.animu_db.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.fangzsx.animu_db.adapters.SearchResultsAdapter
import com.fangzsx.animu_db.databinding.ActivitySearchBinding
import com.fangzsx.animu_db.viewmodels.anime.SearchActivityViewModel

class SearchAnimeActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySearchBinding
    private lateinit var searchVM : SearchActivityViewModel
    private lateinit var searchResultAdapter : SearchResultsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        searchVM = ViewModelProvider(this).get(SearchActivityViewModel::class.java)
        searchResultAdapter = SearchResultsAdapter()

        setContentView(binding.root)

        val searchView = binding.searchView

        setUpResultsRecyclerView()

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {

                query?.let { q ->
                    loadingState()

                    searchVM.getAnimeTitlesByQuery(q)
                    searchVM.searchResults.observe(this@SearchAnimeActivity){ results ->
                        
                        if(results.isNotEmpty()){
                            searchResultAdapter.differ.submitList(results.sortedBy { it.popularity })
                            successState()
                        }else{
                            noResultState()
                        }
                    }
                }
                return false

            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
    }

    private fun noResultState() {
        binding.rvResults.visibility = View.INVISIBLE
        binding.progressBar.visibility = View.INVISIBLE
        binding.tvNoResult.visibility = View.VISIBLE

    }

    private fun loadingState(){
        binding.progressBar.visibility = View.VISIBLE
        binding.rvResults.visibility = View.INVISIBLE
        binding.tvNoResult.visibility = View.INVISIBLE
    }

    private fun successState(){
        binding.progressBar.visibility = View.INVISIBLE
        binding.rvResults.visibility = View.VISIBLE
        binding.tvNoResult.visibility = View.INVISIBLE
    }


    private fun setUpResultsRecyclerView() {

        binding.rvResults.apply {
            layoutManager = GridLayoutManager(this@SearchAnimeActivity, 2, GridLayoutManager.VERTICAL, false)
            adapter = searchResultAdapter
        }

        searchResultAdapter.onItemClick = { animeSearchedData ->
            Intent(this@SearchAnimeActivity, AnimeActivity::class.java).apply {
                putExtra("MAL_ID", animeSearchedData.mal_id)
                putExtra("ANIME_IMAGE_URL", animeSearchedData.images.jpg.large_image_url)
                putExtra("ANIME_TITLE", animeSearchedData.title)

                startActivity(this)
            }
        }
    }
}