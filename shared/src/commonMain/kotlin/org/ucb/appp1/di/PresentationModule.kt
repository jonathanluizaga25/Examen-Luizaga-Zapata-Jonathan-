package org.ucb.appp1.di

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import org.ucb.appp1.earthquakes.presentation.viewmodel.EarthquakesViewModel
import org.ucb.appp1.movies.presentation.viewmodel.MoviesViewModel
import org.ucb.appp1.signin.presentation.viewmodel.LoginViewModel
import org.ucb.appp1.userinformation.presentation.viewmodel.UserInformationViewModel

val presentationModule = module {
    viewModelOf(::LoginViewModel)
    viewModelOf(::UserInformationViewModel)
    viewModelOf(::MoviesViewModel)
    viewModelOf(::EarthquakesViewModel)
}
