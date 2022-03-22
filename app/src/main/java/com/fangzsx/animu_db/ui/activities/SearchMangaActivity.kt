package com.fangzsx.animu_db.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.fangzsx.animu_db.adapters.SearchMangaResultsAdapter
import com.fangzsx.animu_db.databinding.ActivitySearchMangaBinding
import com.fangzsx.animu_db.viewmodels.manga.MangaSearchActivityViewModel

class SearchMangaActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySearchMangaBinding
    private lateinit var searchMangaVM : MangaSearchActivityViewModel
    private lateinit var searchResultsAdapter : SearchMangaResultsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySearchMangaBinding.inflate(layoutInflater)
        searchMangaVM = ViewModelProvider(this).get(MangaSearchActivityViewModel::class.java)
        searchResultsAdapter = SearchMangaResultsAdapter()
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        setupResultsRecyclerView()
        val searchView = binding.searchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                loadingState()

                searchMangaVM.searchMangaTitlesByQuery(query!!)
                searchMangaVM.titles.observe(this@SearchMangaActivity){ searchResults ->

                    if(searchResults.isNotEmpty()){
                        searchResultsAdapter.differ.submitList(searchResults)
                        successState()
                    }else{
                        noResultState()
                    }

                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })


    }

    private fun setupResultsRecyclerView() {
        binding.rvResultsManga.apply{
            layoutManager = GridLayoutManager(this@SearchMangaActivity, 2, GridLayoutManager.VERTICAL, false)
            adapter = searchResultsAdapter
        }

        searchResultsAdapter.onItemClick = { mangaData ->
            Intent(this@SearchMangaActivity, MangaActivity::class.java).apply {
                putExtra("MAL_ID",mangaData.mal_id)
                startActivity(this)
            }
        }
    }

    private fun noResultState() {
        binding.rvResultsManga.visibility = View.INVISIBLE
        binding.progressBar.visibility = View.INVISIBLE
        binding.tvNoResult.visibility = View.VISIBLE

    }

    private fun loadingState(){
        binding.progressBar.visibility = View.VISIBLE
        binding.rvResultsManga.visibility = View.INVISIBLE
        binding.tvNoResult.visibility = View.INVISIBLE
    }

    private fun successState(){
        binding.progressBar.visibility = View.INVISIBLE
        binding.rvResultsManga.visibility = View.VISIBLE
        binding.tvNoResult.visibility = View.INVISIBLE
    }
}