package ch.ranil.kortti.domain.chart

import org.springframework.stereotype.Service
import java.time.Month
import java.time.format.TextStyle
import java.util.*
import kotlin.random.Random

@Service
class DatasetService {

    private val datasets = DatasetType
        .entries
        .associateWith {
            Month
                .entries
                .associate {
                    val month = it.getDisplayName(TextStyle.FULL, Locale.US)
                    month to Random.nextInt(0, 1000)
                }
        }

    fun getDataset(datasetType: DatasetType): Map<String, Int> {
        return datasets[datasetType] ?: emptyMap()
    }

}
