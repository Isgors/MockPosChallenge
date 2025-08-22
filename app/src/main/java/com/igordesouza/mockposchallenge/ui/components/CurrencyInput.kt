package com.igordesouza.mockposchallenge.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.ui.unit.dp
import com.igordesouza.mockposchallenge.R
import com.igordesouza.mockposchallenge.ui.theme.MockPosChallengeTheme
import java.text.NumberFormat
import java.util.Locale

const val MAX_DIGITS = 6
const val MAX_CENTS_VALUE = 999999L

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun CurrencyInput(
    modifier: Modifier = Modifier,
    rawNumericText: String,
    onRawNumericTextChange: (String) -> Unit,
    onValueCentsChange: (cents: Long) -> Unit,
    isEditable: Boolean = true,
    forceFocus: Boolean = false
) {
    var textFieldValue by remember {
        mutableStateOf(TextFieldValue(text = formatCurrency(rawNumericText)))
    }

    val focusRequester = remember { FocusRequester() }
    val textFieldInteractionSource = remember { MutableInteractionSource() }
    val iconInteractionSource = remember { MutableInteractionSource() }

    LaunchedEffect(rawNumericText) {
        val formatted = formatCurrency(rawNumericText)
        textFieldValue = TextFieldValue(text = formatted, selection = TextRange(formatted.length))
        onValueCentsChange(rawNumericText.toLongOrNull() ?: 0L)
    }

    LaunchedEffect(forceFocus) {
        if (forceFocus) {
            focusRequester.requestFocus()
        }
    }

    val isRawNumericTextValid = remember(rawNumericText) {
        (rawNumericText.toLongOrNull() ?: 0L) > 0L &&
                (rawNumericText.toLongOrNull() ?: 0L) <= MAX_CENTS_VALUE
    }

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        OutlinedTextField(
            value = textFieldValue,
            onValueChange = {},
            modifier = Modifier
                .weight(1f)
                .focusRequester(focusRequester)
                .focusable(enabled = isEditable, interactionSource = textFieldInteractionSource),
            prefix = { Text("R$") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
            singleLine = true,
            readOnly = true,
            enabled = isEditable,
            interactionSource = textFieldInteractionSource,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = if (isEditable) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f),
                unfocusedBorderColor = if (isEditable) MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f) else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f),
                focusedTextColor = if (isEditable) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
            )
        )

        Icon(
            painter = painterResource(id = R.drawable.ic_backspace),
            contentDescription = "Backspace (tap) or Clear Input (long press)",
            tint = MaterialTheme.colorScheme.primary.copy(
                alpha = if (isEditable && isRawNumericTextValid) 1f else 0.5f
            ),
            modifier = Modifier
                .padding(start = 12.dp, end = 4.dp)
                .clip(MaterialTheme.shapes.small)
                .combinedClickable(
                    onClick = {
                        if (isEditable && isRawNumericTextValid) {
                            onRawNumericTextChange(rawNumericText.dropLast(1))
                        }
                    },
                    onLongClick = {
                        if (isEditable && isRawNumericTextValid) {
                            onRawNumericTextChange("")
                        }
                    },
                    enabled = isEditable && isRawNumericTextValid,
                    role = Role.Button,
                    interactionSource = iconInteractionSource,
                    indication = ripple(bounded = false)
                )
                .padding(8.dp)
        )
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
private fun CurrencyInputPreview() {
    MockPosChallengeTheme {
        var rawText by remember { mutableStateOf("0") }
        var centsValue by remember { mutableLongStateOf(0L) }
        CurrencyInput(
            rawNumericText = rawText,
            onRawNumericTextChange = { newRawText -> rawText = newRawText },
            onValueCentsChange = { cents -> centsValue = cents },
            isEditable = true,
            forceFocus = true
        )
    }
}

