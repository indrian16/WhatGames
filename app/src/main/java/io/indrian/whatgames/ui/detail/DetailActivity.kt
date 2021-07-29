package io.indrian.whatgames.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import io.indrian.core.data.Resource
import io.indrian.core.di.GlideApp
import io.indrian.core.domain.model.Game
import io.indrian.core.utils.displayDate
import io.indrian.whatgames.R
import io.indrian.whatgames.databinding.ActivityDetailBinding
import io.indrian.core.ui.base.BaseActivity
import io.indrian.whatgames.ui.search.SearchActivity
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class DetailActivity : BaseActivity() {

    private val viewModel: DetailViewModel by viewModel()

    private var _binding: ActivityDetailBinding? = null
    private val binding: ActivityDetailBinding get() = _binding!!

    private val gameObserver = Observer<Resource<Game>> { state ->
        when (state) {
            is Resource.Loading -> {}
            is Resource.Success -> {
                displayGame(state.data)
            }
            is Resource.Error -> {}
            else -> {}
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        intent?.getParcelableExtra<Game>(GAME_EXTRA)?.let { game ->
            displayGame(game)

            viewModel.getGame(game.id).observe(this, gameObserver)
        }
    }

    private fun displayGame(game: Game? = Game()) {
        Timber.d("displayGame: $game")
        with(binding) {
            GlideApp.with(this@DetailActivity)
                .load(game?.backgroundImage)
                .into(imageBackdrop)

            cardMainLayout.tvDate.text = getString(R.string.display_date, game?.updated?.displayDate())
            cardMainLayout.tvTitle.text = game?.name
            cardMainLayout.tvGenres.text = game?.genres?.joinToString(prefix = "# ", separator = ", ") { it.name }

            tvOverviewValue.text = if (!game?.descriptionRaw.isNullOrEmpty()) {
                game?.descriptionRaw
            } else {
                game?.descriptionRaw ?: "-"
            }

            // Game Listener
            cardMainLayout.btnFavorite.isEnabled = game != null
            if (game?.isFavorite == true) {
                cardMainLayout.btnFavorite.setImageResource(R.drawable.ic_love_filled)
            } else {
                cardMainLayout.btnFavorite.setImageResource(R.drawable.ic_love_outlined)
            }

            cardMainLayout.btnFavorite.setOnClickListener {
                if (game != null) {
                    viewModel.setFavorite(game.id)
                }
            }

            cardMainLayout.btnVisit.isEnabled = !game?.website.isNullOrEmpty()
            cardMainLayout.btnVisit.setOnClickListener {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(game?.website)
                    )
                )
            }

            cardMainLayout.btnShareIt.isEnabled = !game?.descriptionRaw.isNullOrEmpty()
            cardMainLayout.btnShareIt.setOnClickListener {
                if (game?.name?.isNotEmpty() == true && game.descriptionRaw.isNotEmpty()) {
                    shareIt(game.name, game.descriptionRaw)
                }
            }
        }
    }

    /**
     * Share Information:
     * Title
     *
     * Overview
     * Text Body
     * */
    private fun shareIt(title: String, overview: String) {
        val text = "$title\n\n${getString(R.string.overview)}\n$overview"
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, text)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, "${getString(R.string.share_it)} $title ?")
        startActivity(shareIntent)
    }

    override fun setupBinding() {
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun initListener() {
        with(binding) {
            imageBack.setOnClickListener { finish() }
            imageSearch.setOnClickListener { SearchActivity.push(this@DetailActivity) }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.getGame().removeObserver(gameObserver)
        _binding = null
    }

    companion object {
        private const val GAME_EXTRA = "game_extra"

        fun push(activity: AppCompatActivity, game: Game) {
            Intent(activity, DetailActivity::class.java).apply {
                putExtra(GAME_EXTRA, game)
            }.run {
                activity.startActivity(this)
            }
        }
    }
}