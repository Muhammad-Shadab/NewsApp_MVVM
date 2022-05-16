package com.example.newsapp.search


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.newsapp.databinding.NewsCardBinding
import com.example.newsapp.home.data.model.Article

class SearchScreenAdapter : RecyclerView.Adapter<SearchScreenAdapter.ViewHolder>() {

    var newsArticles = listOf<Article>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = NewsCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(newsArticles[position]) {
                with(newsCardBinding) {
                    titleTv.text = title
                    descriptionTv.text = description
                    iv.load(urlToImage)
                }
                itemView.setOnClickListener {
                    val action =
                        SearchScreenFragmentDirections.actionSearchScreenFragmentToWebViewFragment(
                            newsArticles[position]
                        )
                    it.findNavController().navigate(action)
                }
            }
        }
    }

    override fun getItemCount() = newsArticles.size

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newsArticles: List<Article>) {
        this.newsArticles = newsArticles
        notifyDataSetChanged()
    }

    inner class ViewHolder(val newsCardBinding: NewsCardBinding) :
        RecyclerView.ViewHolder(newsCardBinding.root)

}