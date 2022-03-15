package com.fangzsx.animu_db.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.fangzsx.animu_db.adapters.SearchResultsAdapter
import com.fangzsx.animu_db.databinding.ActivitySearchBinding
import com.fangzsx.animu_db.viewmodels.anime.SearchActivityViewModel

class SearchActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySearchBinding
    private lateinit var searchVM : SearchActivityViewModel
    private lateinit var searchResultAdapter : SearchResultsAdapter

    override fun onDestroy() {
        super.onDestroy()

    }

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
                    searchVM.searchResults.observe(this@SearchActivity){ results ->
                        
                        if(results.isNotEmpty()){
                            results.sortedByDescending { it.popularity }
                            searchResultAdapter.differ.submitList(results)
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
            layoutManager = GridLayoutManager(this@SearchActivity, 2, GridLayoutManager.VERTICAL, false)
            adapter = searchResultAdapter
        }

        searchResultAdapter.onItemClick = { data ->
            Intent(this@SearchActivity, AnimeActivity::class.java).apply {
                putExtra("MAL_ID", data.mal_id)
                startActivity(this)
                finish()
            }
        }
    }
}