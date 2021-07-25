package io.indrian.core.utils

import io.indrian.core.data.source.local.entity.GameEntity
import io.indrian.core.data.source.local.entity.GenreEntity
import io.indrian.core.data.source.remote.response.GameResponse
import io.indrian.core.data.source.remote.response.GenreResponse
import io.indrian.core.domain.model.Game
import io.indrian.core.domain.model.Genre
import java.util.*

object DataMapper {

    fun mapResponseToDomain(input: List<GameResponse>): List<Game> {
        return input.map {
            Game(
                backgroundImage = it.backgroundImage,
                genres = it.genreResponses.map { genre ->
                    Genre(
                        id = genre.id,
                        gamesCount = genre.gamesCount,
                        imageBackground = genre.imageBackground,
                        name = genre.name,
                    )
                },
                id = it.id,
                name = it.name,
                updated = Date()
            )
        }
    }

    fun mapResponseToEntities(input: List<GameResponse>): List<GameEntity> {
        return input.map {
            GameEntity(
                backgroundImage = it.backgroundImage,
                genres = it.genreResponses.map { genre -> genre.id },
                id = it.id,
                name = it.name,
                updated = Date()
            )
        }
    }

    fun mapEntitiesToDomain(input: List<GameEntity>, genresEntity: List<GenreEntity>): List<Game> {
        return input.map {
            val genres = mutableListOf<Genre>()
            it.genres.forEach { id ->
                val newGenres = genresEntity.filter { filter -> filter.id == id }
                newGenres.forEach { newGenre ->
                    genres.add(
                        Genre(
                            id = newGenre.id,
                            gamesCount = newGenre.gamesCount,
                            imageBackground = newGenre.imageBackground,
                            name = newGenre.name
                        )
                    )
                }
            }
            Game(
                backgroundImage = it.backgroundImage ?: "",
                genres = genres,
                id = it.id,
                name = it.name,
                updated = Date()
            )
        }
    }

    fun mapDomainToEntity(input: Game): GameEntity {
        return GameEntity(
            backgroundImage = input.backgroundImage,
            genres = listOf(),
            id = input.id,
            name = input.name,
            updated = Date()
        )
    }

    fun mapGenreResponseToEntities(input: List<GenreResponse>): List<GenreEntity> {
        return input.map {
            GenreEntity(
                id = it.id,
                gamesCount = it.gamesCount,
                imageBackground = it.imageBackground,
                name = it.name,
            )
        }
    }

    fun mapGenreEntitiesToDomain(input: List<GenreEntity>): List<Genre> {
        return input.map {
            Genre(
                id = it.id,
                gamesCount = it.gamesCount,
                imageBackground = it.imageBackground,
                name = it.name,
            )
        }
    }
}