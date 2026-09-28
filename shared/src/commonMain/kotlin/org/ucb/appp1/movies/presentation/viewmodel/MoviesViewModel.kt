package org.ucb.appp1.movies.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.ucb.appp1.movies.domain.usecase.GetPopularMovies

class MoviesViewModel(
    private val getPopularMovies: GetPopularMovies
) : ViewModel() {
    private val _state = MutableStateFlow(MoviesState())
    val state = _state.asStateFlow()

    init {
        emitEvent(MoviesEvent.LoadMovies)
    }

    fun emitEvent(event: MoviesEvent) {
        when (event) {
            MoviesEvent.LoadMovies, MoviesEvent.Retry -> loadMovies()
        }
    }

    private fun loadMovies() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }

            runCatching { getPopularMovies.invoke() }
                .onSuccess { movies ->
                    _state.update {
                        it.copy(movies = movies, isLoading = false)
                    }
                }
                .onFailure { throwable ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = throwable.message ?: "No se pudieron cargar las películas"
                        )
                    }
                }
        }
    }
}
