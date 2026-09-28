package org.ucb.appp1.movies.presentation.viewmodel

import org.ucb.appp1.movies.domain.model.MovieModel

data class MoviesState(
    val movies: List<MovieModel> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
