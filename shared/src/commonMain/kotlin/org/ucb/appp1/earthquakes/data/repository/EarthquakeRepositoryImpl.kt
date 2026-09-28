package org.ucb.appp1.earthquakes.data.repository

import org.ucb.appp1.earthquakes.data.datasource.EarthquakeRemoteDataSource
import org.ucb.appp1.earthquakes.data.mapper.toDomain
import org.ucb.appp1.earthquakes.domain.model.EarthquakeModel
import org.ucb.appp1.earthquakes.domain.repository.EarthquakeRepository

class EarthquakeRepositoryImpl(
    private val remoteDataSource: EarthquakeRemoteDataSource
) : EarthquakeRepository {
    override suspend fun getStrongEarthquakes(): List<EarthquakeModel> =
        remoteDataSource.getStrongEarthquakes().features.map { it.toDomain() }
}
