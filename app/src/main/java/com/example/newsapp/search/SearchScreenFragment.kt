package com.example.newsapp.search

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.Adapter
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentSearchScreenBinding
import com.example.newsapp.home.data.model.NewsResponse
import com.example.newsapp.home.data.remote.viewmodel.HomeViewModel
import com.example.newsapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchScreenFragment : Fragment() {
    private lateinit var searchScreenBinding: FragmentSearchScreenBinding
    var adapter = SearchScreenAdapter()
    val homeViewModel: HomeViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        searchScreenBinding = FragmentSearchScreenBinding.inflate(inflater, container, false)

        var job: Job? = null
        searchScreenBinding.searchBox.addTextChangedListener { editable ->
            job?.cancel()
            job = MainScope().launch {
                delay(500L)
                editable?.let {
                    if (editable.toString().isNotEmpty()) {
                        homeViewModel.searchNews(editable.toString())

                    }
                }
            }
        }


        homeViewModel.searchNews.observe(
            viewLifecycleOwner
        ) { response ->
            when (response) {
                is Resource.Loading -> {
                }
                is Resource.Success -> {
                    adapter.setData(response.data!!.articles)
                }
                else -> {
                }
            }
        }

        searchScreenBinding.rvSearchScreen.layoutManager = LinearLayoutManager(context)
        searchScreenBinding.rvSearchScreen.adapter = adapter


        return searchScreenBinding.root
    }

}