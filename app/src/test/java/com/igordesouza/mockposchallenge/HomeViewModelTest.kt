package com.igordesouza.mockposchallenge

import app.cash.turbine.test
import com.igordesouza.mockposchallenge.ui.model.PaymentMethod
import com.igordesouza.mockposchallenge.ui.screens.home.HomeAction
import com.igordesouza.mockposchallenge.ui.screens.home.HomeState
import com.igordesouza.mockposchallenge.ui.screens.home.HomeViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.spy
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    private val testDispatcher = StandardTestDispatcher()


    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `initial state is InsertValue with default value`() = runTest {
        val viewModel = HomeViewModel()

        assertEquals(HomeState.InsertValue(), viewModel.state.value)
    }

    @Test
    fun `onAction UpdateValue updates state to InsertValue with new value`() = runTest {
        val viewModel = HomeViewModel()

        val testValue = 12345L
        viewModel.onAction(HomeAction.UpdateValue(testValue))

        testScheduler.advanceUntilIdle()

        assertEquals(HomeState.InsertValue(testValue), viewModel.state.value)
    }

    @Test
    fun `onAction SelectPaymentMethod updates state to ShowPaymentMethods`() = runTest {
        val viewModel = HomeViewModel()

        viewModel.onAction(HomeAction.SelectPaymentMethod)
        assertEquals(HomeState.ShowPaymentMethods, viewModel.state.value)
    }


    @Test
    fun `onAction ProcessPayment updates state to Loading then Success when eightyTwentyChance is true`() = runTest {
        val spiedViewModel = spy(HomeViewModel())

        doReturn(true).whenever(spiedViewModel).eightyTwentyChance()

        spiedViewModel.state.test {
            assertEquals(HomeState.InsertValue(), awaitItem())

            spiedViewModel.onAction(HomeAction.ProcessPayment(PaymentMethod.PIX))

            assertEquals(HomeState.Loading, awaitItem())

            testScheduler.advanceTimeBy(3100L)

            assertEquals(HomeState.Success, awaitItem())

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `onAction ProcessPayment updates state to Loading then Error when eightyTwentyChance is false`() = runTest {
        val spiedViewModel = spy(HomeViewModel())

        doReturn(false).whenever(spiedViewModel).eightyTwentyChance()

        val errorMessage = "Erro ao processar pagamento"

        spiedViewModel.state.test {
            assertEquals(HomeState.InsertValue(), awaitItem())

            spiedViewModel.onAction(HomeAction.ProcessPayment(PaymentMethod.PIX))

            assertEquals(HomeState.Loading, awaitItem())

            testScheduler.advanceTimeBy(3100L)

            val errorState = awaitItem()
            assertTrue("State should be Error", errorState is HomeState.Error)
            assertEquals(errorMessage, (errorState as HomeState.Error).message)

            cancelAndIgnoreRemainingEvents()
        }
    }


    @Test
    fun `onAction RestartFlow updates state to InsertValue with default value`() = runTest {
        val viewModel = HomeViewModel()

        viewModel.onAction(HomeAction.UpdateValue(500L))
        viewModel.onAction(HomeAction.SelectPaymentMethod)
        assertTrue(viewModel.state.value is HomeState.ShowPaymentMethods)

        viewModel.onAction(HomeAction.RestartFlow)

        assertEquals(HomeState.InsertValue(), viewModel.state.value)
    }

    @Test
    fun `onAction GoBack does not change state from InsertValue`() = runTest {
        val viewModel = HomeViewModel()

        val initialState = HomeState.InsertValue(100L)
        viewModel.onAction(HomeAction.UpdateValue(100L))

        viewModel.onAction(HomeAction.GoBack)

        assertEquals(initialState, viewModel.state.value)
    }

}

