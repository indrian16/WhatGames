package io.indrian.core.data.source.remote

import io.indrian.core.data.source.remote.response.GameDetailsResponse
import io.indrian.core.data.source.remote.response.GameResponse
import io.indrian.core.data.source.remote.response.GenreResponse

object FakeRemoteDataSource {

    fun getGames() = listOf(
        GameResponse(
            backgroundImage = "https://media.rawg.io/media/games/5c5/5c5089af36b99bf9465357a8556449f9.jpg",
            genreResponses = listOf(
                GenreResponse(
                    name = "exceptional"
                )
            ),
            id = 52368,
            name = "Shadow of the Colossus  (2018)",
            updated = "2020-06-15T09:34:31"
        )
    )

    fun getGameDetails() = GameDetailsResponse(
        backgroundImage = "https://media.rawg.io/media/games/5c5/5c5089af36b99bf9465357a8556449f9.jpg",
        genres = listOf(
            GenreResponse(
                name = "exceptional"
            )
        ),
        id = 52368,
        name = "Shadow of the Colossus  (2018)",
        updated = "2020-06-15T09:34:31"
    )
}