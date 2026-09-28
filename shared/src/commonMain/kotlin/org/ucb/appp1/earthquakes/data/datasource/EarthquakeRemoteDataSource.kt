package org.ucb.appp1.earthquakes.data.datasource

import org.ucb.appp1.earthquakes.data.dto.EarthquakeResponseDto

interface EarthquakeRemoteDataSource {
    suspend fun getStrongEarthquakes(): EarthquakeResponseDto
}
