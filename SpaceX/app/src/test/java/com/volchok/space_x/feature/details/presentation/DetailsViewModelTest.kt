package com.volchok.space_x.feature.details.presentation

import com.volchok.space_x.feature.details.domain.FetchRocketInfoUseCase
import com.volchok.space_x.feature.model.testRocketDetailsData
import com.volchok.space_x.library.api.domain.ObserveRocketDetailsUseCase
import com.volchok.space_x.library.data.model.Data
import com.volchok.space_x.library.use_case.domain.invoke
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test

internal class DetailsViewModelTest {
    private val fetchRocketInfoUseCase = mockk<FetchRocketInfoUseCase>()
    private val observeRocketDetailsUseCase = mockk<ObserveRocketDetailsUseCase>()

    private fun createViewModel() = DetailsViewModel(
        fetchRocketInfoUseCase,
        observeRocketDetailsUseCase
    )

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        val dispatcher = UnconfinedTestDispatcher()
        Dispatchers.setMain(dispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `should fetch and observe rockets and set loading state and rocket state`() = runTest {
        coEvery { fetchRocketInfoUseCase() } returns Data.Success(testRocketDetailsData)
        coEvery { observeRocketDetailsUseCase() } returns flowOf(testRocketDetailsData)

        val detailsViewModel = createViewModel()
        advanceUntilIdle()

        detailsViewModel.states.value.rocket shouldBe testRocketDetailsData
        detailsViewModel.states.value.loading shouldBe false
        coVerify { fetchRocketInfoUseCase.invoke() }
    }

    @Test
    fun `should have loadingState true and rocketState null as a default state`() {
        coEvery { observeRocketDetailsUseCase.invoke() } returns emptyFlow()

        val detailsViewModel = createViewModel()
        detailsViewModel.states.value.loading shouldBe true
        detailsViewModel.states.value.rocket shouldBe null
    }
}