package org.ucb.appp1.movies.data.service

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.ucb.appp1.movies.data.datasource.MovieRemoteDataSource
import org.ucb.appp1.movies.data.dto.MoviesResponseDto

class TmdbApiService(
    private val apiKey: String
) : MovieRemoteDataSource {
    private val client = HttpClient {
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                }
            )
        }
    }

    override suspend fun getPopularMovies(): MoviesResponseDto =
        client.get("https://api.themoviedb.org/3/discover/movie") {
            parameter("sort_by", "popularity.desc")
            parameter("api_key", apiKey)
        }.body()
}
