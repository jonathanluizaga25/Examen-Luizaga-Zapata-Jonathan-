package org.ucb.appp1.movies.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MoviesResponseDto(
    val page: Int = 0,
    val results: List<MovieDto> = emptyList()
)

@Serializable
data class MovieDto(
    val id: Int = 0,
    val title: String = "",
    val overview: String = "",
    @SerialName("poster_path")
    val posterPath: String? = null
)
