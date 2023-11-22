package ch.ranil.kortti.web

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.slf4j.LoggerFactory

class GiphyClient {
    private val client = HttpClient()
    private val json = Json {
        ignoreUnknownKeys = true
    }

    suspend fun getGifs(query: String): List<GiphyGifs> {
        val rawJson = client
            .get("$SEARCH_URL&q=$query&bundle=messaging_non_clips")
            .bodyAsText()

        LOGGER.debug(rawJson)

        return json
            .decodeFromString<GiphyResponse>(rawJson)
            .data
            .mapNotNull { gifObject ->
                val image = gifObject.images["fixed_height_small"]
                image?.let {
                    GiphyGifs(
                        url = gifObject.images["original"]?.url ?: gifObject.embed_url,
                        title = gifObject.title,
                        previewUrl = image.url
                    )
                }
            }
    }

    companion object {
        private const val API_KEY: String = "OPmZcdJVfrYPB3LsaMeGZ2ZmXdMzB3Qk"
        private const val LIMIT: Int = 20
        private const val SEARCH_URL: String = "https://api.giphy.com/v1/gifs/search?api_key=$API_KEY&limit=$LIMIT"

        private val LOGGER = LoggerFactory.getLogger(GiphyClient::class.java)
    }
}

data class GiphyGifs(
    val url: String,
    val title: String,
    val previewUrl: String
)

@Serializable
data class GiphyResponse(
    val data: List<GifObject>
)

@Serializable
data class GifObject(
    val id: String,
    val embed_url: String,
    val title: String,
    val images: Map<String, ImageObject>
)

@Serializable
data class ImageObject(
    val url: String
)
