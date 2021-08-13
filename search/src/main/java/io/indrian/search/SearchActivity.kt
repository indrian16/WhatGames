package io.indrian.search

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import io.indrian.core.data.Resource
import io.indrian.core.domain.model.Game
import io.indrian.core.ui.adapter.GameAdapter
import io.indrian.core.ui.base.BaseActivity
import io.indrian.core.utils.NavigationManager
import io.indrian.core.utils.textChanges
import io.indrian.search.databinding.ActivitySearchBinding
import io.indrian.search.di.searchModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class SearchActivity : BaseActivity(), GameAdapter.OnGameCallbackListener {

    private var _binding: ActivitySearchBinding? = null
    private val binding: ActivitySearchBinding get() = _binding!!

    private val gameAdapter = GameAdapter(this)

    private val viewModel: SearchViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadKoinModules(searchModule)

        binding.rvSearch.adapter = gameAdapter
        binding.edtSearch.requestFocus()
    }

    override fun setupBinding() {
        _binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    @ExperimentalCoroutinesApi
    @FlowPreview
    override fun initListener() {
        with(binding) {
            imageBack.setOnClickListener { finish() }

            edtSearch.textChanges()
                .filterNot { it.isNullOrBlank() }
                .debounce(300)
                .flatMapLatest { viewModel.search(it) }
                .onEach {
                    when (it) {
                        is Resource.Success -> {
                            gameAdapter.add(it.data)
                        }
                        else -> {}
                    }
                }
                .launchIn(lifecycleScope)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onClickGame(game: Game) {
        NavigationManager.pushDetailGame(this, game)
    }
}