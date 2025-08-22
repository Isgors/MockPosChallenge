package com.igordesouza.mockposchallenge.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.igordesouza.mockposchallenge.ui.theme.MockPosChallengeTheme

@Composable
fun SuccessScreen(
    modifier: Modifier = Modifier,
    message: String,
    icon: ImageVector = Icons.Filled.CheckCircle,
    iconTint: Color = Color(0xFF4CAF50),
    actionButtonText: String? = null,
    onActionButtonClick: (() -> Unit)? = null,
    backgroundColor: Color = MaterialTheme.colorScheme.surface
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = backgroundColor
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = "Success",
                modifier = Modifier.size(80.dp),
                tint = iconTint
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = message,
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center,
//                color = MaterialTheme.colorScheme.surfaceBright
            )

            if (actionButtonText != null && onActionButtonClick != null) {
                Spacer(modifier = Modifier.height(32.dp))
                Button(onClick = onActionButtonClick) {
                    Text(
                        text = actionButtonText,
                        color = MaterialTheme.colorScheme.tertiary
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, name = "Success Screen - No Button")
@Composable
private fun SuccessScreenNoButtonPreview() {
    MockPosChallengeTheme {
        SuccessScreen(message = "Operation completed successfully!")
    }
}

@Preview(showBackground = true, name = "Success Screen - With Button")
@Composable
private fun SuccessScreenWithButtonPreview() {
    MockPosChallengeTheme {
        SuccessScreen(
            message = "Your order has been placed!",
            actionButtonText = "Track Order",
            onActionButtonClick = {}
        )
    }
}

@Preview(showBackground = true, name = "Success Screen - Custom Icon Tint")
@Composable
private fun SuccessScreenCustomTintPreview() {
    MockPosChallengeTheme {
        SuccessScreen(
            message = "Profile Updated!",
            iconTint = Color(0xFF4CAF50) // A nice green color
        )
    }
}
