package org.ucb.appp1.earthquakes.domain.repository

import org.ucb.appp1.earthquakes.domain.model.EarthquakeModel

interface EarthquakeRepository {
    suspend fun getStrongEarthquakes(): List<EarthquakeModel>
}
