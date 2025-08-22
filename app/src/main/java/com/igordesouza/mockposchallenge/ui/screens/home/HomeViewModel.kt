package com.igordesouza.mockposchallenge.ui.screens.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.igordesouza.mockposchallenge.ui.model.PaymentMethod
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.random.Random

class HomeViewModel: ViewModel() {

    private val _state = MutableStateFlow<HomeState>(HomeState.InsertValue())
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        HomeState.InsertValue()
    )

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
        Log.i("HomeViewModel", "Processando pagamento em $paymentMethodSelected")
        viewModelScope.launch {
            _state.update { HomeState.Loading }
            delay(3000L)
            if(eightyTwentyChance())
                _state.update { HomeState.Success }
            else
                _state.update { HomeState.Error("Erro ao processar pagamento") }
        }
    }

    private fun eightyTwentyChance(): Boolean {
        val randomNumber = Random.nextInt(0, 100)
        return randomNumber < 80
    }
}