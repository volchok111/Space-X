package com.volchok.space_x.feature.rockets.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.volchok.space_x.feature.rockets.presentation.RocketsViewModel
import com.volchok.space_x.ui.theme.SpaceXTheme
import io.mockk.mockk
import io.mockk.verify
import org.junit.Rule
import org.junit.Test

class RocketsScreenKtTest {
    @get: Rule
    val composeTestRule = createComposeRule()

    @Test
    fun shouldCallOnItem() {
        val onItem: (String) -> Unit = mockk(relaxed = true)

        composeTestRule.setContent {
            SpaceXTheme {
                RocketsScreenImpl(
                    state = RocketsViewModel.State(),
                    onItem = onItem
                )
            }
        }

        composeTestRule.onNodeWithTag(RocketsScreenTestTags.RocketItemTag)
            .assertIsDisplayed()
            .performClick()

        verify { onItem("") }
    }
}