package com.igordesouza.mockposchallenge.ui.screens.home

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.igordesouza.mockposchallenge.ui.components.CurrencyInput
import com.igordesouza.mockposchallenge.ui.components.MAX_CENTS_VALUE
import com.igordesouza.mockposchallenge.ui.components.MAX_DIGITS
import com.igordesouza.mockposchallenge.ui.components.NumericKeyboard
import com.igordesouza.mockposchallenge.ui.components.PaymentMethodList
import com.igordesouza.mockposchallenge.ui.model.PaymentMethod
import com.igordesouza.mockposchallenge.ui.theme.MockPosChallengeTheme

@Composable
fun HomeContent(
    state: HomeState,
    onAction: (HomeAction) -> Unit
) {
    var rawNumericText by remember { mutableStateOf("0") }
    var amountInCents by remember { mutableLongStateOf(0L) }

    val isPaymentMethodsVisible = state is HomeState.ShowPaymentMethods
    val isCurrencyInputEditable = state !is HomeState.ShowPaymentMethods

    Surface {
        Column {
            CurrencyInput(
                modifier = Modifier.padding(16.dp),
                rawNumericText = rawNumericText,
                onRawNumericTextChange = { newRawText ->
                    rawNumericText = newRawText
                },
                onValueCentsChange = { newCents ->
                    amountInCents = newCents
                    onAction(HomeAction.UpdateValue(newCents))
                },
                isEditable = isCurrencyInputEditable,
                forceFocus = true
            )

            AnimatedContent(
                targetState = isPaymentMethodsVisible,
                label = "InputPaymentTransition",
                transitionSpec = {
                    if (targetState) {
                        slideInHorizontally { fullWidth -> fullWidth } + fadeIn() togetherWith
                                slideOutHorizontally { fullWidth -> -fullWidth } + fadeOut()
                    } else {
                        slideInHorizontally { fullWidth -> -fullWidth } + fadeIn() togetherWith
                                slideOutHorizontally { fullWidth -> fullWidth } + fadeOut()
                    }
                }
            ) { targetIsPaymentMethodsVisible ->
                if (targetIsPaymentMethodsVisible) {
                    PaymentMethodList(
                        paymentMethods = PaymentMethod.entries.toList(),
                        onPaymentMethodSelected = { paymentMethod ->
                            onAction(HomeAction.ProcessPayment(paymentMethodSelected = paymentMethod))
                        }
                    )
                } else {
                    NumericKeyboard(
                        modifier = Modifier.padding(14.dp),
                        onNumberClick = { digitChar ->
                            if (isCurrencyInputEditable && rawNumericText.length < MAX_DIGITS) {
                                val prospectiveText = rawNumericText + digitChar
                                val prospectiveCents = prospectiveText.toLongOrNull() ?: 0L
                                rawNumericText = if (prospectiveCents <= MAX_CENTS_VALUE) {
                                    prospectiveText
                                } else {
                                    MAX_CENTS_VALUE.toString().take(MAX_DIGITS)
                                }
                            }
                        },
                        isConfirmValueButtonEnabled = amountInCents > 0,
                        onConfirmValueButtonClick = {
                            onAction(HomeAction.SelectPaymentMethod)
                        }
                    )
                }
            }
        }
    }
}

// Previews remain the same
@Preview(showBackground = true)
@Composable
private fun HomeContentInsertValuePreview() {
    MockPosChallengeTheme {
        HomeContent(
            state = HomeState.InsertValue(),
            onAction = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeContentShowPaymentMethodsPreview() {
    MockPosChallengeTheme {
        HomeContent(
            state = HomeState.ShowPaymentMethods,
            onAction = {}
        )
    }
}
