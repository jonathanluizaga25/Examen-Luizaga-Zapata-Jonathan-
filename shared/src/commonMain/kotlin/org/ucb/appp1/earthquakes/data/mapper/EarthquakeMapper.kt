package org.ucb.appp1.earthquakes.data.mapper

import org.ucb.appp1.earthquakes.data.dto.EarthquakeFeatureDto
import org.ucb.appp1.earthquakes.domain.model.EarthquakeModel

fun EarthquakeFeatureDto.toDomain(): EarthquakeModel = EarthquakeModel(
    place = properties.place ?: "Ubicacion no disponible",
    magnitude = properties.mag ?: 0.0,
    occurredAtEpochMillis = properties.time ?: 0L,
    eventUrl = properties.url.orEmpty(),
    longitude = geometry.coordinates.getOrNull(0) ?: 0.0,
    latitude = geometry.coordinates.getOrNull(1) ?: 0.0,
    depthKilometers = geometry.coordinates.getOrNull(2) ?: 0.0
)
