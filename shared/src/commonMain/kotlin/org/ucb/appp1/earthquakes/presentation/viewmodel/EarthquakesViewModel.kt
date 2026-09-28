package org.ucb.appp1.earthquakes.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.ucb.appp1.earthquakes.domain.usecase.GetStrongEarthquakes

class EarthquakesViewModel(
    private val getStrongEarthquakes: GetStrongEarthquakes
) : ViewModel() {
    private val _state = MutableStateFlow(EarthquakesState())
    val state = _state.asStateFlow()

    init {
        emitEvent(EarthquakesEvent.LoadEarthquakes)
    }

    fun emitEvent(event: EarthquakesEvent) {
        when (event) {
            EarthquakesEvent.LoadEarthquakes, EarthquakesEvent.Retry -> loadEarthquakes()
        }
    }

    private fun loadEarthquakes() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }

            runCatching { getStrongEarthquakes.invoke() }
                .onSuccess { earthquakes ->
                    _state.update {
                        it.copy(earthquakes = earthquakes, isLoading = false)
                    }
                }
                .onFailure { throwable ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = throwable.message ?: "No se pudieron cargar los terremotos"
                        )
                    }
                }
        }
    }
}
