package com.example.compose.rally

import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import com.example.compose.rally.ui.components.RallyTopAppBar
import org.junit.Rule
import org.junit.Test

/* BEGIN-3.2 - Add the ComposeTestRule */
class TopAppBarTest {

    // This rule lets you set the Compose content under test and interact with
    // it.
    @get:Rule
    val composeTestRule = createComposeRule()

    /* BEGIN-3.3 - Testing in isolation */
    // We could start the app's main activity similarly to how you would do it
    // in the Android View world using Espresso.
    // However, with Compose, we can simplify things considerably by testing a
    // component in isolation.
//    @get:Rule
//    val composeTestRule = createAndroidComposeRule(RallyActivity::class.java)

    @Test
    fun rallyTopAppBarTest_currentTabSelected() {
        // Choose what Compose UI content to use in the test (only once for
        // each test).

        /* BEGIN-3.4 - The importance of a testable Composable */
        // The test shows the top app bar, but it doesn't look as we expected:
        // it has a light theme!
        // The reason is that the bar is built using Material Components, which
        // expect to be within a MaterialTheme, else they fall back to
        // "baseline" styles colors.
        // MaterialTheme has good defaults so it doesn't crash. Since we're not
        // going to test the theme or take screenshots, we can omit it and work
        // with its default light theme.
        composeTestRule.setContent {
            RallyTopAppBar(
                allScreens = RallyScreen.entries,
                onTabSelected = { },
                currentScreen = RallyScreen.Accounts
            )
        }

        /* BEGIN-3.5 - Verify that the tab is selected */
        // Thread.sleep(5000)
        // composeTestRule{.finder}{.assertion}{.action}
        composeTestRule
            .onNodeWithContentDescription(RallyScreen.Accounts.name)
            .assertIsSelected()
        /* END-3.5 */
        /* END-3.4 */
    }
    /* END-3.3 */
}
/* END-3.2 */
