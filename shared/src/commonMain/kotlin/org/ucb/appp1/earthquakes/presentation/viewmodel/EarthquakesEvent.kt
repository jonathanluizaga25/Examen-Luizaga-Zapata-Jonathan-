package org.ucb.appp1.earthquakes.presentation.viewmodel

sealed interface EarthquakesEvent {
    data object LoadEarthquakes : EarthquakesEvent
    data object Retry : EarthquakesEvent
}
