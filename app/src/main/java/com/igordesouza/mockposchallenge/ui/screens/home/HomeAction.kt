package com.igordesouza.mockposchallenge.ui.screens.home

import com.igordesouza.mockposchallenge.ui.model.PaymentMethod


sealed interface HomeAction {
    data object GoBack: HomeAction
    data object RestartFlow: HomeAction
    data class UpdateValue(val value: Long): HomeAction
    data object SelectPaymentMethod: HomeAction
    data class ProcessPayment(val paymentMethodSelected: PaymentMethod): HomeAction
}