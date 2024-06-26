package io.indrian.detailgame

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.lifecycle.Observer
import com.google.android.material.chip.Chip
import io.indrian.core.data.Resource
import io.indrian.core.di.GlideApp
import io.indrian.core.domain.model.Game
import io.indrian.core.ui.base.BaseActivity
import io.indrian.core.utils.*
import io.indrian.detailgame.databinding.ActivityDetailBinding
import io.indrian.detailgame.di.detailGameModule
import io.indrian.whatgames.R
import io.indrian.whatgames.ui.dialogs.FavoriteDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import timber.log.Timber

class DetailActivity : BaseActivity() {

    private val viewModel: DetailViewModel by viewModel()

    private var _binding: ActivityDetailBinding? = null
    private val binding: ActivityDetailBinding get() = _binding!!

    private val gameObserver = Observer<Resource<Game>> { state ->
        when (state) {
            is Resource.Loading -> {
                binding.overviewShimmerContainerLayout.root.show()
                binding.tvOverviewValue.toGone()

                binding.cardMainLayout.detailGenresShimmerContainerLayout.root.show()
                binding.cardMainLayout.chipGenres.toInvisible()
            }
            is Resource.Success -> {
                binding.overviewShimmerContainerLayout.root.hide()
                binding.tvOverviewValue.toVisible()

                binding.cardMainLayout.detailGenresShimmerContainerLayout.root.hide()
                binding.cardMainLayout.chipGenres.toVisible()

                displayGame(state.data)
            }
            is Resource.Error -> {
                binding.overviewShimmerContainerLayout.root.hide()
                binding.tvOverviewValue.toVisible()

                binding.cardMainLayout.detailGenresShimmerContainerLayout.root.hide()
                binding.cardMainLayout.chipGenres.toVisible()
            }
            else -> {}
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadKoinModules(detailGameModule)

        intent?.getParcelableExtra<Game>(NavigationManager.GAME_EXTRA)?.let { game ->
            displayGame(game)

            viewModel.getGame(game.id).observe(this, gameObserver)
        }

        viewModel.updateFavorite.observe(this) { game ->
            game?.let {
                val resourceAnim = if (it.isFavorite) {
                    "add_favorite_anim.json"
                } else {
                    "delete_favorite_anim.json"
                }
                FavoriteDialogFragment.newInstance(resourceAnim)
                    .show(supportFragmentManager, FavoriteDialogFragment.TAG)
            }
        }
    }

    private fun displayGame(game: Game? = Game()) {
        Timber.d("displayGame: $game")
        with(binding) {
            GlideApp.with(this@DetailActivity)
                .load(game?.backgroundImage)
                .into(imageBackdrop)

            cardMainLayout.tvDate.text = game?.updated?.displayDate()
            cardMainLayout.tvTitle.text = game?.name
            cardMainLayout.chipGenres.removeAllViews()
            game?.genres?.forEach {
                cardMainLayout.chipGenres.addView(
                    Chip(this@DetailActivity).apply {
                        text = it.name
                    }
                )
            }

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
            imageSearch.setOnClickListener {
                Intent(
                    this@DetailActivity,
                    Class.forName("io.indrian.search.SearchActivity")
                ).run {
                    startActivity(this)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.getGame().removeObserver(gameObserver)
        _binding = null
    }
}