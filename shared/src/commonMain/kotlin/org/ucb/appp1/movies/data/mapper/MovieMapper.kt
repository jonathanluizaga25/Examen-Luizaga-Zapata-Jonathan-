package org.ucb.appp1.movies.data.mapper

import org.ucb.appp1.movies.data.dto.MovieDto
import org.ucb.appp1.movies.domain.model.MovieModel
import org.ucb.appp1.movies.domain.vo.PosterPath

private const val POSTER_BASE_URL = "https://image.tmdb.org/t/p/w500"

fun MovieDto.toDomain(): MovieModel = MovieModel(
    title = title,
    description = overview,
    posterPath = PosterPath(
        posterPath?.takeIf { it.isNotBlank() }?.let { "$POSTER_BASE_URL$it" }.orEmpty()
    )
)
