package com.fangzsx.animu_db.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import com.fangzsx.animu_db.adapters.SearchResultsAdapter
import com.fangzsx.animu_db.databinding.ActivitySearchBinding
import com.fangzsx.animu_db.viewmodels.anime.SearchActivityViewModel

class SearchActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySearchBinding
    private lateinit var searchVM : SearchActivityViewModel
    private lateinit var searchResultAdapter : SearchResultsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        searchVM = SearchActivityViewModel()
        searchResultAdapter = SearchResultsAdapter()

        setContentView(binding.root)

        val searchView = binding.searchView

        setUpResultsRecyclerView()

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {

                query?.let { q ->
                    searchVM.getAnimeTitlesByQuery(q)
                    searchVM.searchResults.observe(this@SearchActivity){ results ->
                        searchResultAdapter.differ.submitList(results)
                    }
                }
                return false

            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })


    }

    private fun setUpResultsRecyclerView() {
        binding.rvResults.apply {
            layoutManager = GridLayoutManager(this@SearchActivity, 2, GridLayoutManager.VERTICAL, false)
            adapter = searchResultAdapter
        }
    }
}