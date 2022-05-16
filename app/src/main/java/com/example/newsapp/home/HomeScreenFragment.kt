package com.example.newsapp.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.Adapter
import com.example.newsapp.databinding.FragmentHomeScreenBinding
import com.example.newsapp.home.data.remote.viewmodel.HomeViewModel
import com.example.newsapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeScreenFragment : Fragment() {
    val homeViewModel: HomeViewModel by viewModels()
    var homeScreenBinding: FragmentHomeScreenBinding? = null
    var adapter = Adapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeScreenBinding = FragmentHomeScreenBinding.inflate(inflater, container, false)

        homeViewModel.getHeadlines("us", 1)
        lifecycleScope.launchWhenCreated {
            homeViewModel._headlines.collect { response ->
                when (response) {
                    is Resource.Loading -> {

                    }
                    is Resource.Success -> {
                        homeScreenBinding?.progressBar?.visibility = View.INVISIBLE
                        adapter.setData(response.data!!.articles)
                    }
                    else -> {}
                }
            }
        }
        homeScreenBinding!!.rv.layoutManager = LinearLayoutManager(context)
        homeScreenBinding!!.rv.adapter = adapter
        return homeScreenBinding?.root
    }
}