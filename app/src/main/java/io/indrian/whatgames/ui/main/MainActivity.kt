package io.indrian.whatgames.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import io.indrian.core.data.Resource
import io.indrian.core.domain.model.Game
import io.indrian.core.domain.model.Genre
import io.indrian.core.ui.adapter.GameAdapter
import io.indrian.core.ui.adapter.GenreAdapter
import io.indrian.core.ui.base.BaseActivity
import io.indrian.core.utils.toGone
import io.indrian.core.utils.toVisible
import io.indrian.whatgames.databinding.ActivityMainBinding
import io.indrian.whatgames.ui.detail.DetailActivity
import io.indrian.whatgames.ui.search.SearchActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity(), GameAdapter.OnGameCallbackListener {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding get() = _binding!!

    private val genreAdapter = GenreAdapter()
    private val gameReleasedAdapter = GameAdapter(this)
    private val gameRatingAdapter = GameAdapter(this)

    private val viewModel: MainViewModel by viewModel()

    private val gameReleasedObserver = Observer<Resource<List<Game>>> { state ->
        when (state) {
            is Resource.Loading -> {
            }
            is Resource.Success -> {
                binding.tvReleased.toVisible()
                binding.rvReleased.toVisible()

                gameReleasedAdapter.add(state.data)
            }
            is Resource.Error -> {
                binding.tvReleased.toGone()
                binding.rvReleased.toGone()
            }
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

    private val genresObserver = Observer<Resource<List<Genre>>> { state ->
        when (state) {
            is Resource.Loading -> {
                binding.shimmerGenreContainer.root.startShimmer()
                binding.shimmerGenreContainer.root.visibility = View.VISIBLE
            }
            is Resource.Success -> {
                binding.shimmerGenreContainer.root.stopShimmer()
                binding.shimmerGenreContainer.root.visibility = View.INVISIBLE
                genreAdapter.add(state.data)
            }
            is Resource.Error -> {
                binding.shimmerGenreContainer.root.stopShimmer()
                binding.shimmerGenreContainer.root.visibility = View.INVISIBLE
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.rvGenres.adapter = genreAdapter
        binding.rvReleased.adapter = gameReleasedAdapter
        binding.rvRating.adapter = gameRatingAdapter

        viewModel.gamesReleased.observe(this, gameReleasedObserver)
        viewModel.gamesRating.observe(this, gameRatingObserver)
        viewModel.genres.observe(this, genresObserver)
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

    override fun onClickGame(game: Game) {
        DetailActivity.push(this, game)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.gamesReleased.removeObserver(gameReleasedObserver)
        viewModel.gamesRating.removeObserver(gameRatingObserver)
        viewModel.genres.removeObserver(genresObserver)
        _binding = null
    }
}