package com.example.newsapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.newsapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    var mainBinding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        mainBinding?.apply {
            setContentView(root)
            val navController =
                (supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment).navController
            /*setupActionBarWithNavController(navController)*/
            bottomNavigationMenu.setupWithNavController(navController)
        }
    }
}