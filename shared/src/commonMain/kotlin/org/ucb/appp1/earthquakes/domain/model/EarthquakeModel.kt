package org.ucb.appp1.earthquakes.domain.model

data class EarthquakeModel(
    val place: String,
    val magnitude: Double,
    val occurredAtEpochMillis: Long,
    val eventUrl: String,
    val longitude: Double,
    val latitude: Double,
    val depthKilometers: Double

)
