package com.igordesouza.mockposchallenge.ui.screens

import androidx.compose.material.icons.filled.Warning
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
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
fun ErrorScreen(
    modifier: Modifier = Modifier,
    message: String,
    detailedError: String? = null,
    icon: ImageVector = Icons.Filled.Warning,
    iconTint: Color = MaterialTheme.colorScheme.error,
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
                contentDescription = "Error",
                modifier = Modifier.size(80.dp),
                tint = iconTint
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = message,
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onErrorContainer
            )

            if (detailedError != null) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = detailedError,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            if (actionButtonText != null && onActionButtonClick != null) {
                Spacer(modifier = Modifier.height(32.dp))
                Button(onClick = onActionButtonClick) {
                    Text(
                        text = actionButtonText,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, name = "Error Screen - No Button")
@Composable
private fun ErrorScreenNoButtonPreview() {
    MockPosChallengeTheme {
        ErrorScreen(message = "Something went wrong.")
    }
}

@Preview(showBackground = true, name = "Error Screen - With Retry")
@Composable
private fun ErrorScreenWithRetryPreview() {
    MockPosChallengeTheme {
        ErrorScreen(
            message = "Failed to load data.",
            detailedError = "Network request timed out. Please check your connection.",
            actionButtonText = "Retry",
            onActionButtonClick = {}
        )
    }
}

@Preview(showBackground = true, name = "Error Screen - Custom Action")
@Composable
private fun ErrorScreenCustomActionPreview() {
    MockPosChallengeTheme {
        ErrorScreen(
            message = "Oops! An unknown error occurred.",
            actionButtonText = "Report Issue",
            onActionButtonClick = {}
        )
    }
}
