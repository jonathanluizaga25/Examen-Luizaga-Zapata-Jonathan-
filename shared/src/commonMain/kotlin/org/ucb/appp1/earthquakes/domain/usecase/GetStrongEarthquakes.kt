package org.ucb.appp1.earthquakes.domain.usecase

import org.ucb.appp1.earthquakes.domain.model.EarthquakeModel
import org.ucb.appp1.earthquakes.domain.repository.EarthquakeRepository

class GetStrongEarthquakes(
    private val repository: EarthquakeRepository
) {
    suspend fun invoke(): List<EarthquakeModel> = repository.getStrongEarthquakes()
}