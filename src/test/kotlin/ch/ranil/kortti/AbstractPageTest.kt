package ch.ranil.kortti

import com.microsoft.playwright.Browser
import com.microsoft.playwright.BrowserType
import com.microsoft.playwright.Page
import com.microsoft.playwright.Playwright
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.TestInstance
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // Allows for cleaner setup if needed
abstract class AbstractPageTest {

    @LocalServerPort
    protected var port: Int = 0

    protected lateinit var playwright: Playwright
    protected lateinit var browser: Browser
    protected lateinit var page: Page

    fun modifyLaunchOptions(options: BrowserType.LaunchOptions) = options

    @BeforeEach
    fun baseSetUp() {
        val isCi = System.getenv("CI") != null
        playwright = Playwright.create()
        browser = playwright.chromium().launch(
            modifyLaunchOptions(
                BrowserType.LaunchOptions()
                    .setHeadless(true)
                    .setSlowMo(if (isCi) 0.0 else 300.0)
            )
        )
        val context = browser.newContext()
        page = context.newPage()
    }

    @AfterEach
    fun baseTearDown() {
        browser.close()
        playwright.close()
    }

    // Helper to get the base URL automatically
    protected fun navigate(path: String = "/") {
        page.navigate("http://localhost:$port$path")
    }
}
