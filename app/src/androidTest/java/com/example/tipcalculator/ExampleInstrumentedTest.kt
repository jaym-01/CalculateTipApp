import androidx.compose.foundation.layout.Box
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import com.example.tipcalculator.MainApp
import com.example.tipcalculator.ui.theme.TipCalculatorTheme
import org.junit.Rule
import org.junit.Test
import java.text.NumberFormat

class TipUITests{
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun cal_20_per_tip(){
        composeTestRule.setContent {
            TipCalculatorTheme {
                MainApp()
            }
        }
        composeTestRule.onNodeWithText("Total Cost").performTextInput("10")

        composeTestRule.onNodeWithText("Tip Percentage").performTextInput("20")

        val expectedTip = NumberFormat.getCurrencyInstance().format(2)
        composeTestRule.onNodeWithText("Tip amount: $expectedTip").assertExists("No node with this text exists")


    }
}