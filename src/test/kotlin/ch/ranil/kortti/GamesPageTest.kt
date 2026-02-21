package ch.ranil.kortti

import com.microsoft.playwright.BrowserType
import com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat
import com.microsoft.playwright.options.AriaRole
import org.junit.jupiter.api.Test
import java.util.regex.Pattern

class GamesPageTest : AbstractPageTest() {

    override fun modifyLaunchOptions(options: BrowserType.LaunchOptions): BrowserType.LaunchOptions {
        return options.setSlowMo(10.0)
    }

    @Test
    fun `should play game and verify high score persists after refresh`() {
        navigate("/games")

        // 1. Start the game
        page.locator("#start-btn").click()

        val box = page.locator("div.box")
        val finalScoreLocator = page.getByText(Pattern.compile("Final Score: .*"))

        // 2. The Game Loop
        while (!finalScoreLocator.isVisible) {
            if (box.isVisible) {
                // Using force(true) to ensure we click even during animations
                box.click(com.microsoft.playwright.Locator.ClickOptions().setForce(true))
            }
        }

        // 3. Extract the actual score value (e.g., "42" from "Final Score: 42")
        val finalScoreText = finalScoreLocator.textContent()
        val scoreValue = finalScoreText.replace("Final Score: ", "").trim()

        // 4. Submit the score
        page.getByRole(AriaRole.BUTTON, com.microsoft.playwright.Page.GetByRoleOptions().setName("Submit")).click()

        // 5. Refresh the page
        page.reload()

        // 6. Verify "High Score: [scoreValue]" appears
        val highScoreLocator = page.getByText("High Score: $scoreValue")
        assertThat(highScoreLocator).isVisible()

        println("Verified High Score: $scoreValue")
    }
}