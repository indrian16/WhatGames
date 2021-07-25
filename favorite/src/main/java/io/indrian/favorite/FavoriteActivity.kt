package io.indrian.favorite

import android.os.Bundle
import androidx.lifecycle.Observer
import io.indrian.core.domain.model.Game
import io.indrian.core.ui.GameAdapter
import io.indrian.favorite.databinding.ActivityFavoriteBinding
import io.indrian.favorite.di.favoriteModule
import io.indrian.whatgames.ui.base.BaseActivity
import io.indrian.whatgames.ui.detail.DetailActivity
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : BaseActivity(), GameAdapter.OnGameCallbackListener {

    private val viewModel: FavoriteViewModel by viewModel()

    private var _binding: ActivityFavoriteBinding? = null
    private val binding: ActivityFavoriteBinding get() = _binding!!

    private val gameAdapter = GameAdapter(this)

    private val gamesObserver = Observer<List<Game>> { data ->
        gameAdapter.add(data)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.toolbar)
        loadKoinModules(favoriteModule)

        binding.rvFavorite.adapter = gameAdapter

        viewModel.games.observe(this, gamesObserver)
    }

    override fun setupBinding() {
        _binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun initListener() {
        binding.imageBack.setOnClickListener { finish() }
    }

    override fun onClickGame(game: Game) {
        DetailActivity.push(this, game)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.games.removeObserver(gamesObserver)
        _binding = null
    }
}