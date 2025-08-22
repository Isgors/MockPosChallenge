package com.igordesouza.mockposchallenge.ui.screens.home

sealed class HomeState {
    data class InsertValue(val value: Long = 0L): HomeState()
    data object ShowPaymentMethods: HomeState()
    data object Loading: HomeState()
    data class Error(val message: String): HomeState()
    data object Success: HomeState()

}