package org.ucb.appp1.movies.data.repository

import org.ucb.appp1.movies.data.datasource.MovieRemoteDataSource
import org.ucb.appp1.movies.data.mapper.toDomain
import org.ucb.appp1.movies.domain.model.MovieModel
import org.ucb.appp1.movies.domain.repository.MovieRepository

class MovieRepositoryImpl(
    private val remoteDataSource: MovieRemoteDataSource
) : MovieRepository {
    override suspend fun getMovies(): List<MovieModel> =
        remoteDataSource.getPopularMovies().results.map { it.toDomain() }
}
