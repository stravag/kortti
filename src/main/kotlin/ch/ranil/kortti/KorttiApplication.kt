package ch.ranil.kortti

import ch.ranil.kortti.templates.DynamicTemplates
import ch.ranil.kortti.templates.Templates
import gg.jte.ContentType
import gg.jte.TemplateEngine
import gg.jte.resolve.DirectoryCodeResolver
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.nio.file.Path

@SpringBootApplication
class KorttiApplication

fun main(args: Array<String>) {
    runApplication<KorttiApplication>(*args)
}

@Configuration
class TemplateConfig {
    @Bean
    fun dynamicTemplates(): Templates {
        val resolver = DirectoryCodeResolver(Path.of("src/main/jte"))
        val templateEngine = TemplateEngine.create(resolver, ContentType.Html)
        return DynamicTemplates(templateEngine)
    }
}
