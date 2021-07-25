package io.indrian.whatgames.ui.search

import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import io.indrian.core.data.Resource
import io.indrian.core.domain.model.Game
import io.indrian.core.ui.GameAdapter
import io.indrian.core.utils.textChanges
import io.indrian.whatgames.databinding.ActivitySearchBinding
import io.indrian.whatgames.ui.base.BaseActivity
import io.indrian.whatgames.ui.detail.DetailActivity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import org.koin.android.viewmodel.ext.android.viewModel

class SearchActivity : BaseActivity(), GameAdapter.OnGameCallbackListener {

    private var _binding: ActivitySearchBinding? = null
    private val binding: ActivitySearchBinding get() = _binding!!

    private val gameAdapter = GameAdapter(this)

    private val viewModel: SearchViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
                .flatMapLatest { viewModel.search(it.toString()) }
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
        DetailActivity.push(this, game)
    }

    companion object {
        fun push(activity: AppCompatActivity) {
            activity.run {
                startActivity(
                    Intent(this, SearchActivity::class.java)
                )
            }
        }
    }
}