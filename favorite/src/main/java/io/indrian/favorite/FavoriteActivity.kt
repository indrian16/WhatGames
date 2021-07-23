package io.indrian.favorite

import android.os.Bundle
import android.widget.Toast
import io.indrian.favorite.databinding.ActivityFavoriteBinding
import io.indrian.whatgames.adapter.GameAdapter
import io.indrian.whatgames.ui.base.BaseActivity

class FavoriteActivity : BaseActivity() {

    private var _binding: ActivityFavoriteBinding? = null
    private val binding: ActivityFavoriteBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.toolbar)
        binding.rvFavorite.adapter = GameAdapter()
    }

    override fun setupBinding() {
        _binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun initListener() {
        binding.imageBack.setOnClickListener { finish() }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}