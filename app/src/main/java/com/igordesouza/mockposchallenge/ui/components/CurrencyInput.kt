package com.igordesouza.mockposchallenge.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.igordesouza.mockposchallenge.ui.theme.MockPosChallengeTheme
import java.text.NumberFormat
import java.util.Locale

const val MAX_DIGITS = 6
const val MAX_CENTS_VALUE = 999999L

@Composable
fun CurrencyInput(
    modifier: Modifier = Modifier,
    initialValueCents: Long = 0L,
    onValueChange: (cents: Long) -> Unit,
    label: String = "R$ 0,00"
) {
    var rawNumericText by remember {
        val initialNumeric = if (initialValueCents > 0) (initialValueCents).toString() else ""
        mutableStateOf(initialNumeric)
    }

    var textFieldValue by remember {
        mutableStateOf(TextFieldValue(text = formatCurrency(rawNumericText)))
    }

    LaunchedEffect(rawNumericText) {
        val currentCents = rawNumericText.toLongOrNull() ?: 0L
        onValueChange(currentCents)
        val formatted = formatCurrency(rawNumericText)
        textFieldValue = TextFieldValue(text = formatted, selection = TextRange(formatted.length))
    }

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        OutlinedTextField(
            value = textFieldValue,
            onValueChange = { newTextFieldValue ->
                val newRawNumericText = newTextFieldValue.text.filter { it.isDigit() }

                if (newRawNumericText.length <= MAX_DIGITS) {
                    val prospectiveCents = newRawNumericText.toLongOrNull() ?: 0L
                    if (prospectiveCents <= MAX_CENTS_VALUE) {
                        rawNumericText = newRawNumericText
                        textFieldValue = newTextFieldValue.copy(text = formatCurrency(newRawNumericText))
                    } else {
                        rawNumericText = MAX_CENTS_VALUE.toString().take(MAX_DIGITS)
                        textFieldValue = newTextFieldValue.copy(text = formatCurrency(rawNumericText))
                    }
                }
            },
            modifier = Modifier.weight(1f),
            label = { Text(label) },
            prefix = { Text("R$") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword), // NumberPassword to avoid non-digits
            singleLine = true
        )

        IconButton(
            onClick = {
                if (rawNumericText.isNotEmpty()) {
                    rawNumericText = rawNumericText.dropLast(1)
                }
            },
            modifier = Modifier.padding(start = 8.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Delete,
                contentDescription = "Delete Button",
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}

private fun formatCurrency(numericText: String): String {
    if (numericText.isEmpty()) return ""

    val valueLong = numericText.toLongOrNull() ?: 0L
    val dollars = valueLong / 100
    val cents = valueLong % 100

    val numberFormat = NumberFormat.getNumberInstance(
        Locale("pt", "BR")
    ).apply {
        maximumFractionDigits = 0
        minimumFractionDigits = 0
    }
    val formattedDollars = numberFormat.format(dollars)

    return "$formattedDollars,${String.format("%02d", cents)}"
}


@Preview(showBackground = true)
@Composable
fun CurrencyInputPreview() {
    MockPosChallengeTheme {
        var amountInCents by remember { mutableLongStateOf(55232L) }
        CurrencyInput(
            modifier = Modifier.padding(16.dp),
            initialValueCents = amountInCents,
            onValueChange = { newCents ->
                amountInCents = newCents
            },
            label = "Enter Amount"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CurrencyInputEmptyPreview() {
    MockPosChallengeTheme {
        var amountInCents by remember { mutableLongStateOf(0L) }
        CurrencyInput(
            modifier = Modifier.padding(16.dp),
            initialValueCents = amountInCents,
            onValueChange = { newCents ->
                amountInCents = newCents
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CurrencyInputMaxPreview() {
    MockPosChallengeTheme {
        var amountInCents by remember { mutableLongStateOf(999999L) }
        CurrencyInput(
            modifier = Modifier.padding(16.dp),
            initialValueCents = amountInCents,
            onValueChange = { newCents ->
                amountInCents = newCents
            },
            label = "Max Amount"
        )
    }
}
