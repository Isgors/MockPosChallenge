package com.igordesouza.mockposchallenge.ui.screens.home

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.igordesouza.mockposchallenge.ui.screens.ErrorScreen
import com.igordesouza.mockposchallenge.ui.screens.LoadingScreen
import com.igordesouza.mockposchallenge.ui.screens.SuccessScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreenRoot(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Surface(modifier = modifier) {
        HomeScreen(state = state, onAction = viewModel::onAction)
    }

}

@Composable
fun HomeScreen(
    state: HomeState,
    onAction: (HomeAction) -> Unit,
) {
    when(state) {
        HomeState.Loading -> LoadingScreen(loadingText = "Processando pagamento...")
        is HomeState.Error -> {
            ErrorScreen(
                message = state.message,
                actionButtonText = "Tentar novamente",
                onActionButtonClick = {onAction(HomeAction.RestartFlow)}
            )
        }
        HomeState.Success -> {
            SuccessScreen(
                message = "Pagamento Realizado com Sucesso!",
                actionButtonText = "Realizar outro pagamento",
                onActionButtonClick = {onAction(HomeAction.RestartFlow)}
            )
        }
        is HomeState.InsertValue, HomeState.ShowPaymentMethods -> {
            HomeContent(
                state = state,
                onAction = onAction,
            )
        }
    }
}

