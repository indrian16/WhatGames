package io.indrian.core.utils

import io.indrian.core.data.source.local.entity.GameEntity
import io.indrian.core.data.source.remote.response.GameResponse
import io.indrian.core.domain.model.Game
import java.util.*

object DataMapper {

    fun mapResponseToEntities(input: List<GameResponse>): List<GameEntity> {
        return input.map {
            GameEntity(
                backgroundImage = it.backgroundImage,
                genres = listOf(),
                id = it.id,
                name = it.name,
                updated = Date()
            )
        }
    }

    fun mapEntitiesToDomain(input: List<GameEntity>): List<Game> {
        return input.map {
            Game(
                backgroundImage = it.backgroundImage ?: "",
                genres = listOf(),
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
}