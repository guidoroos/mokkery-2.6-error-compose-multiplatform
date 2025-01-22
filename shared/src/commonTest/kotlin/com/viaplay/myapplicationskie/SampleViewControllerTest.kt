package com.viaplay.myapplicationskie

import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.mock
import dev.mokkery.verifySuspend
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class SampleViewControllerTest {

    private lateinit var sampleViewController: SampleViewController
    private lateinit var sampleRepository: SampleRepository

    @BeforeTest
    fun setUp() {
        sampleRepository = mock {
            everySuspend { getData() } returns listOf("testing", "result")
        }

        sampleViewController = SampleViewController(sampleRepository = sampleRepository)
    }

    @Test
    fun testDataUpdated() = runTest {
        sampleViewController.getData()

        verifySuspend { sampleRepository.getData() }

    }
}