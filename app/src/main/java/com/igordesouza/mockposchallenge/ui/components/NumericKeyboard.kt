package com.igordesouza.mockposchallenge.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.igordesouza.mockposchallenge.ui.theme.MockPosChallengeTheme

@Composable
fun NumericKeyboard(
    modifier: Modifier = Modifier,
    onNumberClick: (String) -> Unit,
    isConfirmValueButtonEnabled: Boolean = true,
    onConfirmValueButtonClick: () -> Unit = {}
) {
    val numbers = (1..9).map { it.toString() }
    val spacing = 16.dp
    val horizontalGridPadding = 16.dp

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BoxWithConstraints(modifier = Modifier.padding(horizontal = horizontalGridPadding)) {
            val totalWidth = maxWidth - (spacing * 2)
            val itemWidth = totalWidth / 3

            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                userScrollEnabled = false,
                verticalArrangement = Arrangement.spacedBy(spacing),
                horizontalArrangement = Arrangement.spacedBy(spacing),
                contentPadding = PaddingValues(vertical = spacing)
            ) {
                items(numbers) { number ->
                    NumberButton(
                        number = number,
                        onClick = { onNumberClick(number) },
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = (itemWidth * 3) + (spacing * 3) + spacing),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                NumberButton(
                    number = "00",
                    onClick = { onNumberClick("00") },
                    modifier = Modifier.width(itemWidth)
                )
                NumberButton(
                    number = "0",
                    onClick = { onNumberClick("0") },
                    modifier = Modifier.width(itemWidth)
                )
                Button(
                    onClick = { onConfirmValueButtonClick() },
                    enabled = isConfirmValueButtonEnabled,
                    shape = CircleShape,
                    modifier = Modifier
                        .aspectRatio(1f),
                    contentPadding = PaddingValues(0.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.tertiary
                    )
                ) {
                    Icon(
                        imageVector = Icons.Filled.CheckCircle,
                        contentDescription = "ProcessPaymentButton"
                    )
                }
            }
        }
    }
}

@Composable
private fun NumberButton(
    number: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        shape = CircleShape,
        modifier = modifier
            .aspectRatio(1f),
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.tertiary
        )
    ) {
        Text(
            text = number,
            fontSize = 24.sp,
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@Preview(showBackground = true, widthDp = 300)
@Composable
fun NumericKeyboardPreview() {
    MockPosChallengeTheme {
        NumericKeyboard(onNumberClick = { number -> println("Clicked: $number") })
    }
}

@Preview(showBackground = true, widthDp = 200)
@Composable
fun NumericKeyboardSmallPreview() {
    MockPosChallengeTheme {
        NumericKeyboard(onNumberClick = { number -> println("Clicked: $number") })
    }
}
