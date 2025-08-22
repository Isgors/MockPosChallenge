package com.igordesouza.mockposchallenge.di

import com.igordesouza.mockposchallenge.ui.screens.home.HomeViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::HomeViewModel)
}