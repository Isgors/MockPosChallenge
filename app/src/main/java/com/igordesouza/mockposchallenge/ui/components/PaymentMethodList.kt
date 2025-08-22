package com.igordesouza.mockposchallenge.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.igordesouza.mockposchallenge.ui.model.PaymentMethod
import com.igordesouza.mockposchallenge.ui.theme.MockPosChallengeTheme

@Composable
fun PaymentMethodList(
    modifier: Modifier = Modifier,
    paymentMethods: List<PaymentMethod>,
    onPaymentMethodSelected: (PaymentMethod) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(15.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(paymentMethods) { paymentMethod ->
            PaymentMethodItem(
                paymentMethod = paymentMethod,
                onPaymentMethodSelected = onPaymentMethodSelected
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentMethodItem(
    paymentMethod: PaymentMethod,
    onPaymentMethodSelected: (PaymentMethod) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = { onPaymentMethodSelected(paymentMethod) },
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.tertiary,
            disabledContainerColor = MaterialTheme.colorScheme.primary,
            disabledContentColor = MaterialTheme.colorScheme.tertiary
        )
    ) {
        Text(
            modifier = Modifier.padding(28.dp),
            text = paymentMethod.description,
            style = MaterialTheme.typography.headlineMedium
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PaymentMethodListPreview() {
    MockPosChallengeTheme {
        PaymentMethodList(
            paymentMethods = PaymentMethod.entries.toList(),
            onPaymentMethodSelected = {}
        )
    }
}
