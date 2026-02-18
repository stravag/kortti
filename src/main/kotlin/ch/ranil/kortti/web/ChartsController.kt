package ch.ranil.kortti.web

import ch.ranil.kortti.domain.chart.DatasetService
import ch.ranil.kortti.domain.chart.DatasetType
import ch.ranil.kortti.templates.Templates
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/charts", produces = ["text/html"])
class ChartsController(
    private val templates: Templates,
    private val datasetService: DatasetService,
) {

    @GetMapping
    fun page(): String {
        return templates.pageCharts().render()
    }

    @GetMapping("/dataset", produces = ["application/json"])
    fun addDataPoint(@RequestParam datasetType: DatasetType): Map<String, Any> {
        val dataset = datasetService.getDataset(datasetType)

        return mapOf(
            "labels" to dataset.keys,
            "datasets" to listOf(
                mapOf("data" to dataset.values)
            ),
        )
    }
}
