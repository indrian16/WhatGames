package io.indrian.whatgames.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import io.indrian.core.data.Resource
import io.indrian.core.domain.model.Game
import io.indrian.core.ui.GameAdapter
import io.indrian.core.ui.GenreAdapter
import io.indrian.whatgames.databinding.ActivityMainBinding
import io.indrian.whatgames.ui.base.BaseActivity
import io.indrian.whatgames.ui.search.SearchActivity
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding get() = _binding!!

    private val genreAdapter = GenreAdapter()
    private val gameReleasedAdapter = GameAdapter()
    private val gameRatingAdapter = GameAdapter()

    private val viewModel: MainViewModel by viewModel()

    private val gameReleasedObserver = Observer<Resource<List<Game>>> { state ->
        when (state) {
            is Resource.Loading -> { }
            is Resource.Success -> {
                gameReleasedAdapter.add(state.data)
            }
            is Resource.Error -> { }
        }
    }

    private val gameRatingObserver = Observer<Resource<List<Game>>> { state ->
        when (state) {
            is Resource.Loading -> { }
            is Resource.Success -> {
                gameRatingAdapter.add(state.data)
            }
            is Resource.Error -> { }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.rvGenres.adapter = genreAdapter
        binding.rvReleased.adapter = gameReleasedAdapter
        binding.rvRating.adapter = gameRatingAdapter

        viewModel.gamesReleased.observe(this, gameReleasedObserver)
        viewModel.gamesRating.observe(this, gameRatingObserver)
    }

    override fun setupBinding() {
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun initListener() {
        with(binding) {
            imageFavorite.setOnClickListener {
                Intent(
                    it.context,
                    Class.forName("io.indrian.favorite.FavoriteActivity")
                ).run {
                    startActivity(this)
                }
            }
            imageSearch.setOnClickListener { SearchActivity.push(this@MainActivity) }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.gamesReleased.removeObserver(gameReleasedObserver)
        viewModel.gamesRating.removeObserver(gameRatingObserver)
        _binding = null
    }
}