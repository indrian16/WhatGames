package io.indrian.core.utils

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import io.indrian.core.domain.model.Game

object NavigationManager {

    const val GAME_EXTRA = "game_extra"

    fun pushDetailGame(activity: AppCompatActivity, game: Game) {
        Intent(activity, Class.forName("io.indrian.detailgame.DetailActivity")).apply {
            putExtra(GAME_EXTRA, game)
        }.run {
            activity.startActivity(this)
        }
    }
}