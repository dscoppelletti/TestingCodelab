package com.example.compose.rally

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasParent
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.isSelected
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.printToLog
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

    /* BEGIN-4 - Debugging tests */
    @Test
    fun rallyTopAppBarTest_currentLabelExists() {
        composeTestRule.setContent {
            RallyTopAppBar(
                allScreens = RallyScreen.entries,
                onTabSelected = { },
                currentScreen = RallyScreen.Accounts
            )
        }

        /* BEGIN-5 - Merged and unmerged Semantics trees */
        // Compose exposes these Semantics properties automatically in some
        // composables such as Text. You can also customize and merge them to
        // represent a single component made up of one or multiple descendants.
        // For example: you can represent a Button containing a Text composable.
        // The property MergeDescendants = 'true' is telling us that this node
        // had descendants, but they have been merged into it. In tests we
        // oftentimes need to access all nodes.
        // composeTestRule.onRoot().printToLog("currentLabelExists")
// 01-17 17:07:16.204 23674 23695 D currentLabelExists: printToLog:
// Printing with useUnmergedTree = 'false'
// Node #1 at (l=0.0, t=63.0, r=1080.0, b=210.0)px
//  |-Node #2 at (l=0.0, t=63.0, r=1080.0, b=210.0)px
//    IsTraversalGroup = 'true'
//  |-Node #3 at (l=0.0, t=63.0, r=1080.0, b=210.0)px
//    [SelectableGroup]
//  |-Node #4 at (l=42.0, t=105.0, r=105.0, b=168.0)px
//  | ContentDescription = '[Overview]'
//  | Selected = 'false'
//  | Role = 'Tab'
//  | Focused = 'false'
//  | Actions = [OnClick, RequestFocus]
//  | MergeDescendants = 'true'
//  | ClearAndSetSemantics = 'true'
//  |-Node #6 at (l=189.0, t=105.0, r=469.0, b=168.0)px
//  | ContentDescription = '[Accounts]'
//  | Selected = 'true'
//  | Role = 'Tab'
//  | Focused = 'false'
//  | Actions = [OnClick, RequestFocus]
//  | MergeDescendants = 'true'
//  | ClearAndSetSemantics = 'true'
//  |-Node #10 at (l=553.0, t=105.0, r=616.0, b=168.0)px
//    ContentDescription = '[Bills]'
//    Selected = 'false'
//    Role = 'Tab'
//    Focused = 'false'
//    Actions = [OnClick, RequestFocus]
//    MergeDescendants = 'true'
//    ClearAndSetSemantics = 'true'
        composeTestRule.onRoot(useUnmergedTree = true)
            .printToLog("currentLabelExists")
// Both nodes #4 and #10 still have no descendants, but node 6, the selected
// tab, has one and we can now see the ‘Text' property.
// 01-17 17:38:35.205 24420 24441 D currentLabelExists: printToLog:
// Printing with useUnmergedTree = 'true'
// Node #1 at (l=0.0, t=63.0, r=1080.0, b=210.0)px
//  |-Node #2 at (l=0.0, t=63.0, r=1080.0, b=210.0)px
//    IsTraversalGroup = 'true'
//     |-Node #3 at (l=0.0, t=63.0, r=1080.0, b=210.0)px
//       [SelectableGroup]
//        |-Node #4 at (l=42.0, t=105.0, r=105.0, b=168.0)px
//        | ContentDescription = '[Overview]'
//        | Selected = 'false'
//        | Role = 'Tab'
//        | Focused = 'false'
//        | Actions = [OnClick, RequestFocus]
//        | MergeDescendants = 'true'
//        | ClearAndSetSemantics = 'true'
//        |-Node #6 at (l=189.0, t=105.0, r=469.0, b=168.0)px
//        | ContentDescription = '[Accounts]'
//        | Selected = 'true'
//        | Role = 'Tab'
//        | Focused = 'false'
//        | Actions = [OnClick, RequestFocus]
//        | MergeDescendants = 'true'
//        | ClearAndSetSemantics = 'true'
//        |  |-Node #9 at (l=284.0, t=105.0, r=469.0, b=154.0)px
//        |    Text = '[ACCOUNTS]'
//        |    Actions = [GetTextLayoutResult]
//        |-Node #10 at (l=553.0, t=105.0, r=616.0, b=168.0)px
//          ContentDescription = '[Bills]'
//          Selected = 'false'
//          Role = 'Tab'
//          Focused = 'false'
//          Actions = [OnClick, RequestFocus]
//          MergeDescendants = 'true'
//          ClearAndSetSemantics = 'true'
        /* END-5 */

        // Composables don't have IDs and you can't use the Node numbers shown
        // in the tree to match them. If matching a node with its semantics
        // properties is impractical or impossible, you can use the testTag
        // modifier with the hasTestTag matcher as a last resort.

        // This fails
//        composeTestRule
//            .onNodeWithText(RallyScreen.Accounts.name.uppercase())
//            .assertExists()

        // This succeds but this test is not very useful!
        // If you look at the Semantics tree closely, the content descriptions
        // of all three tabs are there whether or not their tab is selected. We
        // must go deeper!
        /* BEGIN-5 - Merged and unmerged Semantics trees */
//        composeTestRule
//            .onNodeWithContentDescription(RallyScreen.Accounts.name)
//            .assertExists()
        composeTestRule
            .onNode(
                hasText(RallyScreen.Accounts.name.uppercase()) and
                        // In this case, strictly, you don't have to add the
                        // parent to the matcher because it's a very isolated
                        // test. However, it's a good idea to avoid using broad
                        // finders alone (such as hasText) which might fail in
                        // larger tests (when other instances of the text might
                        // be found).
                        hasParent(
                            hasContentDescription(RallyScreen.Accounts.name)
                        ),
                useUnmergedTree = true
            )
            .assertExists()
        /* END-5 */
    }
    /* END-4 */

    /* BEGIN-7 - Optional excercise */
    // Verify that clicking on the different tabs of the RallyTopAppBar changes
    // the selection.
    @Test
    fun rallyTopAppBarTest_clickedTabSelected() {
        composeTestRule.setContent {
            var currentScreen by rememberSaveable {
                mutableStateOf(RallyScreen.Overview)
            }

            RallyTopAppBar(
                allScreens = RallyScreen.entries,
                onTabSelected = { screen -> currentScreen = screen },
                currentScreen = currentScreen
            )
        }

        composeTestRule
            .onNodeWithContentDescription(RallyScreen.Overview.name)
            .assert(isSelected()) { "Initial" }

        composeTestRule
            .onNodeWithContentDescription(RallyScreen.Accounts.name)
            .performClick()
            .assert(isSelected()) { RallyScreen.Accounts.name }

        composeTestRule
            .onNodeWithContentDescription(RallyScreen.Bills.name)
            .performClick()
            .assert(isSelected()) { RallyScreen.Bills.name }

        composeTestRule
            .onNodeWithContentDescription(RallyScreen.Overview.name)
            .performClick()
            .assert(isSelected()) { RallyScreen.Overview.name }
    }
    /* END-7 */

}
/* END-3.2 */
