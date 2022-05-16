package com.example.newsapp.webview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.newsapp.databinding.FragmentWebviewBinding
import com.example.newsapp.home.data.local.db.ArticleDatabase
import com.example.newsapp.home.data.local.repository.ArticleRepository
import com.example.newsapp.home.data.local.viewmodel.ArticleViewModel
import com.example.newsapp.home.data.model.Article
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WebViewFragment : Fragment() {
    private var saved: Boolean = false
    private val args: WebViewFragmentArgs by navArgs()
    val articleViewModel: ArticleViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val webViewBinding = FragmentWebviewBinding.inflate(layoutInflater, container, false)

        webViewBinding.floatingBtn.setOnClickListener {
            if (!saved) {
                articleViewModel.insertUpdate(args.article)
                Toast.makeText(context, "Saved successfully", Toast.LENGTH_SHORT).show()
                saved = true
            } else {
                Toast.makeText(context, "Already saved", Toast.LENGTH_SHORT).show()
            }
        }

        webViewBinding.webView.setWebViewClient(WebViewClient())
        webViewBinding.webView.loadUrl(args.article.url)

        return webViewBinding.root
    }
}