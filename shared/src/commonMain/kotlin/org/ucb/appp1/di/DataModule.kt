package org.ucb.appp1.di

import org.koin.dsl.module
import org.ucb.appp1.earthquakes.data.datasource.EarthquakeRemoteDataSource
import org.ucb.appp1.earthquakes.data.repository.EarthquakeRepositoryImpl
import org.ucb.appp1.earthquakes.data.service.UsgsApiService
import org.ucb.appp1.earthquakes.domain.repository.EarthquakeRepository
import org.ucb.appp1.movies.data.datasource.MovieRemoteDataSource
import org.ucb.appp1.movies.data.repository.MovieRepositoryImpl
import org.ucb.appp1.movies.data.service.TmdbApiService
import org.ucb.appp1.movies.domain.repository.MovieRepository
import org.ucb.appp1.userinformation.data.datasource.GithubRemoteDataSource
import org.ucb.appp1.userinformation.data.repository.GithubRepositoryImpl
import org.ucb.appp1.userinformation.data.service.GitHubApiService
import org.ucb.appp1.userinformation.domain.repository.GithubRepository

val dataModule = module {
    single<GithubRemoteDataSource> { GitHubApiService() }
    single<GithubRepository>{GithubRepositoryImpl(get())}
    single<MovieRemoteDataSource> {
        TmdbApiService(apiKey = getProperty("tmdbApiKey"))
    }
    single<MovieRepository> { MovieRepositoryImpl(get()) }
    single<EarthquakeRemoteDataSource> { UsgsApiService() }
    single<EarthquakeRepository> { EarthquakeRepositoryImpl(get()) }
}
