package ch.ranil.kortti

import com.microsoft.playwright.BrowserType
import com.microsoft.playwright.Page.GetByRoleOptions
import com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat
import com.microsoft.playwright.options.AriaRole
import org.junit.jupiter.api.Test

class MemesPageTest : AbstractPageTest() {

    override fun modifyLaunchOptions(options: BrowserType.LaunchOptions): BrowserType.LaunchOptions {
        return options.setSlowMo(100.0)
    }

    @Test
    fun `should wait for meme image to load`() {
        // 1. Navigate to the specific sub-route
        navigate("/memes")

        // 2. Initial State: Check for the "Waiting" text
        val container = page.locator("[sse-swap='meme']")
        assertThat(container).containsText("Waiting for memes...")

        // Ensure no image exists yet
        assertThat(page.locator("img")).hasCount(0)

        // 3. Act: Click the button that triggers the backend broadcast
        page.getByRole(AriaRole.BUTTON, GetByRoleOptions().setName("Send Meme")).click()

        // 4. Assert: htmx swaps the content.
        // Playwright's assertThat is web-first and will poll until the condition is met.
        val memeImage = page.locator("img")

        // This will wait up to 5 seconds for the SSE event to arrive and htmx to render it
        assertThat(memeImage).isVisible()

        // 5. Final check: Verify the old text is gone and image has a source
        assertThat(page.getByText("Waiting for memes...")).hasCount(0)
    }
}
