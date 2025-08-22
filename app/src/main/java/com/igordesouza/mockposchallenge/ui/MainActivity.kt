package com.igordesouza.mockposchallenge.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import com.igordesouza.mockposchallenge.ui.screens.home.HomeScreenRoot
import com.igordesouza.mockposchallenge.ui.theme.MockPosChallengeTheme
import com.igordesouza.mockposchallenge.BuildConfig
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.res.stringResource
import com.igordesouza.mockposchallenge.R

class MainActivity : ComponentActivity() {

    val appFlavor = BuildConfig.FLAVOR

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MockPosChallengeTheme {
                Scaffold(
                    modifier = Modifier.Companion.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(text = stringResource(id = R.string.app_name))
                            }
                        )
                    }
                ) { paddingValues ->
                    HomeScreenRoot(
                        modifier = Modifier.padding(paddingValues)
                    )
                }
            }
        }
    }
}