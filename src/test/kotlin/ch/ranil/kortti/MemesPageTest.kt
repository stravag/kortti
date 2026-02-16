package ch.ranil.kortti

import com.microsoft.playwright.assertions.LocatorAssertions
import com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat
import org.junit.jupiter.api.Test

class MemesPageTest : AbstractPageTest() {

    @Test
    fun `should wait for meme image to load`() {
        // 1. Navigate to the specific sub-route
        navigate("/memes")

        // 2. Locate the image (using a generic 'img' selector or a role)
        val memeImage = page.locator("img")

        // 3. Wait up to 20 seconds for the image to be visible
        // We use the 'assertion' approach which is the cleanest in Playwright
        assertThat(memeImage).isVisible(
            LocatorAssertions.IsVisibleOptions().setTimeout(20000.0)
        )
    }
}
