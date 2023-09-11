package com.volchok.space_x.feature.details.domain

import com.volchok.space_x.feature.model.testRocketDetailsData
import com.volchok.space_x.library.api.data.RocketRepository
import com.volchok.space_x.library.api.model.details.RocketDetailsModel
import com.volchok.space_x.library.data.model.Data
import com.volchok.space_x.library.memory.details.domain.GetSelectedRocketIdUseCase
import com.volchok.space_x.library.memory.details.domain.LocalRocketRepository
import com.volchok.space_x.library.use_case.domain.invoke
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

class FetchRocketInfoUseCaseTest {
    private val testId = "aaa"
    private val rocketRepository = mockk<RocketRepository>()
    private val localRocketRepository = mockk<LocalRocketRepository>()
    private val getSelectedRocketIdUseCase = mockk<GetSelectedRocketIdUseCase>()

    @Test
    fun `should fetch rocket info`() = runTest {

        coEvery { getSelectedRocketIdUseCase(Unit) } returns testId
        coEvery { localRocketRepository.rocket } returns flowOf(testRocketDetailsData)
        coEvery { localRocketRepository.set(any()) } just runs
        coEvery { rocketRepository.getRocketInfo(any()) } returns Data.Success(
            testRocketDetailsData
        )

        val fetchRocketInfoUseCase = FetchRocketInfoUseCase(
            rocketRepository,
            localRocketRepository,
            getSelectedRocketIdUseCase
        )

        val result = fetchRocketInfoUseCase()
        result.shouldBeInstanceOf<Data.Success<RocketDetailsModel>>()
        result.value shouldBe testRocketDetailsData

        coVerify { rocketRepository.getRocketInfo(testId) }
    }
}