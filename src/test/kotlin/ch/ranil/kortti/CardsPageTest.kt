package ch.ranil.kortti

import ch.ranil.kortti.domain.card.CardType
import com.microsoft.playwright.Browser
import com.microsoft.playwright.BrowserType
import com.microsoft.playwright.Page
import com.microsoft.playwright.Page.GetByRoleOptions
import com.microsoft.playwright.Playwright
import com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat
import com.microsoft.playwright.options.AriaRole
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CardsPageTest {

    @LocalServerPort
    private var port: Int = 0

    private lateinit var playwright: Playwright
    private lateinit var browser: Browser
    private lateinit var page: Page

    @BeforeEach
    fun setUp() {
        playwright = Playwright.create()
        browser = playwright.chromium().launch(
            BrowserType.LaunchOptions()
                .setHeadless(false)
                .setSlowMo(300.0)
        )
        val context = browser.newContext()
        page = context.newPage()
    }

    @AfterEach
    fun tearDown() {
        // Essential to close resources to prevent memory leaks/zombie browsers
        browser.close()
        playwright.close()
    }

    @Test
    fun `can create and delete card`() {
        page.navigate("http://localhost:$port/")
        page.addCard("test1")
        page.addCard("test2")
        page.addCard("test3")

        page.deleteCard("test2")
    }

    private fun Page.addCard(name: String, type: CardType? = null) {
        type?.let { getByLabel("Type").selectOption(type.name) }
        getByRole(AriaRole.TEXTBOX, GetByRoleOptions().setName("Content")).click()
        getByRole(AriaRole.TEXTBOX, GetByRoleOptions().setName("Content")).fill(name)
        getByRole(AriaRole.BUTTON, GetByRoleOptions().setName("Create")).click()
        assertThat(getByRole(AriaRole.CELL, GetByRoleOptions().setName(name))).isVisible()
    }

    private fun Page.deleteCard(name: String) {
        getByRole(AriaRole.ROW, GetByRoleOptions().setName(name))
            .getByRole(AriaRole.BUTTON).click()
        assertThat(getByRole(AriaRole.CELL, GetByRoleOptions().setName(name))).not().isVisible()
    }
}