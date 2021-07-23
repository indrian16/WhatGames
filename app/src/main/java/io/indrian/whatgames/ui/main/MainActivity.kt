package io.indrian.whatgames.ui.main

import android.content.Intent
import android.os.Bundle
import io.indrian.whatgames.adapter.GameAdapter
import io.indrian.whatgames.adapter.GenreAdapter
import io.indrian.whatgames.databinding.ActivityMainBinding
import io.indrian.whatgames.ui.base.BaseActivity
import io.indrian.whatgames.ui.search.SearchActivity

class MainActivity : BaseActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding get() = _binding!!

    private val genreAdapter = GenreAdapter()
    private val gameAdapter = GameAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.rvGenres.adapter = genreAdapter
        binding.rvReleased.adapter = gameAdapter
        binding.rvRating.adapter = gameAdapter
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
        _binding = null
    }
}