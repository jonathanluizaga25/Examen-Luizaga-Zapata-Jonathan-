package org.ucb.appp1.earthquakes.presentation.viewmodel

import org.ucb.appp1.earthquakes.domain.model.EarthquakeModel

data class EarthquakesState(
    val earthquakes: List<EarthquakeModel> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null


)