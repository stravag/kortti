package ch.ranil.htmx.persistence

import ch.ranil.htmx.domain.AdventCalendarRepository
import org.slf4j.LoggerFactory

class AdventCalendarRepositoryImpl : AdventCalendarRepository {
    override fun save() {
        LOGGER.info("save")
    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(AdventCalendarRepositoryImpl::class.java)
    }
}
