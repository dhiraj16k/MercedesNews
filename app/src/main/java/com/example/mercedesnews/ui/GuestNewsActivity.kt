package com.example.mercedesnews.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.mercedesnews.R
import com.example.mercedesnews.db.ArticleDatabase
import com.example.mercedesnews.repository.NewsRepository
import kotlinx.android.synthetic.main.activity_guest_news.*

class GuestNewsActivity : AppCompatActivity() {

    lateinit var viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guest_news)

        val newsRepository = NewsRepository(ArticleDatabase(this))
        val viewModelProviderFactory = NewsViewModelProviderFactory(newsRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(NewsViewModel::class.java)

        guestBottomNavigationView.setupWithNavController(guestNewsNavHostFragment.findNavController())

        supportActionBar?.hide()
    }
}