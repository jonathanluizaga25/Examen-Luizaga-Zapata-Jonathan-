package org.ucb.appp1.movies.presentation.viewmodel

sealed interface MoviesEvent {
    data object LoadMovies : MoviesEvent
    data object Retry : MoviesEvent
}
