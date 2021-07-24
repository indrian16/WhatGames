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
    private val gameAdapter = GameAdapter()

    private val viewModel: MainViewModel by viewModel()

    private val gameObserver = Observer<Resource<List<Game>>> { state ->
        when (state) {
            is Resource.Loading -> {
                binding.swipeLayout.isRefreshing = true
            }
            is Resource.Success -> {
                binding.swipeLayout.isRefreshing = false

                gameAdapter.add(state.data)
            }
            is Resource.Error -> {
                binding.swipeLayout.isRefreshing = false
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.rvGenres.adapter = genreAdapter
        binding.rvReleased.adapter = gameAdapter
        binding.rvRating.adapter = gameAdapter

        viewModel.games.observe(this, gameObserver)
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
        viewModel.games.removeObserver(gameObserver)
        _binding = null
    }
}