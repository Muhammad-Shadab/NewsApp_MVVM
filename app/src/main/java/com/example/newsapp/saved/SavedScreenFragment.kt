package com.example.newsapp.saved

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.animation.AnimatedContentScope.SlideDirection.Companion.Up
import androidx.compose.ui.focus.FocusDirection.Companion.Up
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.Adapter
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentSavedScreenBinding
import com.example.newsapp.home.data.local.viewmodel.ArticleViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SavedScreenFragment : Fragment() {
    private lateinit var savedScreenBinding: FragmentSavedScreenBinding
    val articleViewModel: ArticleViewModel by viewModels()
    val adapter = Adapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        savedScreenBinding = FragmentSavedScreenBinding.inflate(inflater, container, false)

        savedScreenBinding.rvSavedScreen.layoutManager = LinearLayoutManager(context)

        ItemTouchHelper(getItemCallback()).apply {
            attachToRecyclerView(savedScreenBinding.rvSavedScreen)
        }


        articleViewModel.getArticle().observe(
            this.viewLifecycleOwner
        ) {
            adapter.setData(it)
            savedScreenBinding.rvSavedScreen.adapter = adapter
        }

        return savedScreenBinding.root

    }

    private fun getItemCallback(): ItemTouchHelper.SimpleCallback {
        val itemTouchHelperCallBack = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val article = adapter.newsArticles[position]
                articleViewModel.delete(article)
                view?.let {
                    Snackbar.make(it, "Successfully deleted article", Snackbar.LENGTH_LONG).apply {
                        setAction("Undo") {
                            articleViewModel.insertUpdate(article)
                        }
                        show()
                    }
                }
            }
        }
        return itemTouchHelperCallBack
    }

}