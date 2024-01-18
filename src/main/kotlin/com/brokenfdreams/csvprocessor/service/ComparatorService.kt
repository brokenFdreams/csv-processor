package com.brokenfdreams.csvprocessor.service

import com.brokenfdreams.csvprocessor.model.Status
import org.springframework.stereotype.Service
import java.io.InputStream

@Service
class ComparatorService(
    val csvReaderService: CsvReaderService,
    val jsonReaderService: JsonReaderService,
    val csvWriterService: CsvWriterService
) {
    fun compareCsvToJsonField(
        csvInputStream: InputStream,
        csvFieldName: String,
        jsonInputStream: InputStream,
        jsonFieldPath: List<String>
    ): String {
        val jsonValues = jsonReaderService.readJsonValues(jsonInputStream, jsonFieldPath).keys
        val csvValues = csvReaderService.readOneColumnToList(csvInputStream, csvFieldName)

        val result = csvValues.map { listOf(it, (if (it in jsonValues) Status.PRESENT else Status.MISSING).name) }
            .toList()
        return csvWriterService.writeMap(result)
    }
}
