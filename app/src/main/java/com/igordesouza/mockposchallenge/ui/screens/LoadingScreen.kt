package com.igordesouza.mockposchallenge.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.igordesouza.mockposchallenge.ui.theme.MockPosChallengeTheme

@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier,
    loadingText: String? = null,
    progressIndicatorColor: Color = MaterialTheme.colorScheme.primary,
    progressIndicatorSize: Dp = 48.dp,
    scrimColor: Color = MaterialTheme.colorScheme.surface.copy(alpha = 0.5f)
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = scrimColor
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(progressIndicatorSize),
                color = progressIndicatorColor,
                strokeWidth = 4.dp
            )

            if (loadingText != null) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = loadingText,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

@Preview(showBackground = true, name = "Loading Screen without Text")
@Composable
private fun LoadingScreenPreview() {
    MockPosChallengeTheme {
        LoadingScreen()
    }
}

@Preview(showBackground = true, name = "Loading Screen with Text")
@Composable
private fun LoadingScreenWithTextPreview() {
    MockPosChallengeTheme {
        LoadingScreen(loadingText = "Carregando...")
    }
}
