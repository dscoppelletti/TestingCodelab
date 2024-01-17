package com.example.compose.rally

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.compose.rally.ui.overview.OverviewBody
import org.junit.Rule
import org.junit.Test

/* BEGIN-6 - Synchronization */
// Any test that you write must be properly synchronized with the subject under
// test. For example, when you use a finder such as onNodeWithText, the test
// waits until the app is idle before querying the semantics tree. Without
// synchronization, tests could look for elements before they're displayed or
// they could wait unnecessarily.
//
// If you run this test, you'll notice it never finishes (it times out
// after 30 seconds).
// androidx.compose.ui.test.junit4.android.ComposeNotIdleException:
//     Idling resource timed out: possibly due to compose being busy.
// IdlingResourceRegistry has the following idling resources registered:
// - [busy] androidx.compose.ui.test.junit4.android.ComposeIdlingResource@d075f91
//
// Let's replace the animation in OverviewBody by an explicitly infinite
// animation.
// Then, if you run the test, it will pass.
class OverviewScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun overviewScreen_alertsDisplayed() {
        composeTestRule.setContent {
            OverviewBody()
        }

        composeTestRule
            .onNodeWithText("Alerts")
            .assertIsDisplayed()
    }
}
/* END-6 */

