package io.indrian.whatgames.ui.search

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import io.indrian.core.data.Resource
import io.indrian.core.domain.model.Game
import io.indrian.core.ui.GameAdapter
import io.indrian.whatgames.databinding.ActivitySearchBinding
import io.indrian.whatgames.ui.base.BaseActivity
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class SearchActivity : BaseActivity() {

    private var _binding: ActivitySearchBinding? = null
    private val binding: ActivitySearchBinding get() = _binding!!

    private val gameAdapter = GameAdapter()

    private val viewModel: SearchViewModel by viewModel()

    private val searchGamesObserver = Observer<Resource<List<Game>>> { state ->
        when (state) {
            is Resource.Loading -> {
                gameAdapter.clear()
            }
            is Resource.Success -> {
                gameAdapter.add(state.data)
            }
            is Resource.Error -> { }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.rvSearch.adapter = gameAdapter

        viewModel.searchGames.observe(this, searchGamesObserver)
    }

    override fun setupBinding() {
        _binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun initListener() {
        with(binding) {
            imageBack.setOnClickListener { finish() }

            edtSearch.addTextChangedListener {
                if (it != null && it.isNotEmpty()) {
                    Timber.d("edtSearch: $it")
                    viewModel.searchGames(it.toString())
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.searchGames.removeObserver(searchGamesObserver)
        _binding = null
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