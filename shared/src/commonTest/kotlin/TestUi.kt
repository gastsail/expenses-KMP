import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.runComposeUiTest
import kotlin.test.Test

class TestUi {
    @OptIn(ExperimentalTestApi::class)
    @Test
    fun shouldAssertTextString() = runComposeUiTest {
        setContent {
            Text(text = "Important things!", modifier = Modifier.testTag("info"))
        }
        onNodeWithTag("info").assertTextContains("Important things!", substring = true)
    }
}