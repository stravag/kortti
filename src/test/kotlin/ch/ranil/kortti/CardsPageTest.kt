package ch.ranil.kortti

import ch.ranil.kortti.domain.card.CardType
import com.microsoft.playwright.Page
import com.microsoft.playwright.Page.GetByRoleOptions
import com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat
import com.microsoft.playwright.options.AriaRole
import org.junit.jupiter.api.Test

class CardsPageTest : AbstractPageTest() {

    @Test
    fun `can create and delete card`() {
        navigate("/")
        page.deleteCard("To the moon")
        page.deleteCard("You're old mate!")

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