package io.indrian.core.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.indrian.core.databinding.GameItemLayoutBinding
import io.indrian.core.di.GlideApp
import io.indrian.core.domain.model.Game

class GameAdapter : RecyclerView.Adapter<GameAdapter.ViewHolder>() {

    private var games: List<Game> = arrayListOf()

    fun add(games: List<Game>?) {
        if (games?.isNotEmpty() == true) {
            this.games = games
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            GameItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(games[position])
    }

    override fun getItemCount(): Int = games.size

    inner class ViewHolder(private val itemBinding: GameItemLayoutBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(game: Game) {
            with(itemBinding) {
                tvTitle.text = game.name
                if (game.genres.isNotEmpty()) {
                    tvGenre.text = game.genres.first().toString()
                }
                GlideApp.with(root)
                    .load(game.backgroundImage)
                    .into(imageGame)

                cardGame.setOnClickListener {
//                    val intent = Intent(itemBinding.root.context, DetailActivity::class.java)
//                    itemBinding.root.context.startActivity(intent)
                }
            }
        }
    }
}