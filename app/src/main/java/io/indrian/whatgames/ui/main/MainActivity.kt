package io.indrian.whatgames.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.indrian.whatgames.adapter.GameAdapter
import io.indrian.whatgames.adapter.GenreAdapter
import io.indrian.whatgames.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding get() = _binding!!

    private val genreAdapter = GenreAdapter()
    private val gameAdapter = GameAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvGenres.adapter = genreAdapter
        binding.rvReleased.adapter = gameAdapter
        binding.rvRating.adapter = gameAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}