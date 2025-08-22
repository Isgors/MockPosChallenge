package com.igordesouza.mockposchallenge.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.igordesouza.mockposchallenge.ui.model.PaymentMethod
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.random.Random

class HomeViewModel: ViewModel() {

    private val _state = MutableStateFlow<HomeState>(HomeState.InsertValue())
    val state = _state.asStateFlow()

    fun onAction(action: HomeAction) {
        when(action) {
            is HomeAction.ProcessPayment -> {
                processPayment(action.paymentMethodSelected)
            }
            is HomeAction.SelectPaymentMethod -> _state.update { HomeState.ShowPaymentMethods }
            is HomeAction.UpdateValue -> _state.update { HomeState.InsertValue(action.value) }
            HomeAction.RestartFlow -> _state.update { HomeState.InsertValue() }
            HomeAction.GoBack -> Unit
        }
    }

    private fun processPayment(paymentMethodSelected: PaymentMethod) {
        viewModelScope.launch {
            _state.update { HomeState.Loading }
            delay(3000L)
            if(eightyTwentyChance())
                _state.update { HomeState.Success }
            else
                _state.update { HomeState.Error("Erro ao processar pagamento") }
        }
    }

    fun eightyTwentyChance(): Boolean {
        val randomNumber = Random.nextInt(0, 100)
        return randomNumber < 80
    }
}