package org.ucb.appp1.movies.data.datasource

import org.ucb.appp1.movies.data.dto.MoviesResponseDto

interface MovieRemoteDataSource {
    suspend fun getPopularMovies(): MoviesResponseDto
}
