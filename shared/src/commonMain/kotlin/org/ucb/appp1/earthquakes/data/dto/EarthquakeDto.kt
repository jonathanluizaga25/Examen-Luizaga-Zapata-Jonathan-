package org.ucb.appp1.earthquakes.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class EarthquakeResponseDto(
    val features: List<EarthquakeFeatureDto> = emptyList()
)

@Serializable
data class EarthquakeFeatureDto(
    val properties: EarthquakePropertiesDto = EarthquakePropertiesDto(),
    val geometry: EarthquakeGeometryDto = EarthquakeGeometryDto()
)

@Serializable
data class EarthquakePropertiesDto(
    val place: String? = null,
    val mag: Double? = null,
    val time: Long? = null,
    val url: String? = null
)

@Serializable
data class EarthquakeGeometryDto(
    val coordinates: List<Double> = emptyList()
)
