package com.brokenfdreams.csvprocessor.service

import org.springframework.stereotype.Service
import java.io.InputStream

@Service
class JsonProcessorService(private val jsonReaderService: JsonReaderService) {
    fun addNewValues(
        originalInputStream: InputStream,
        newValuesInputStream: InputStream,
        jsonFieldPath: List<String>
    ): Map<String, String> {
        val newValues = jsonReaderService.readJsonValues(newValuesInputStream, emptyList())
        return jsonReaderService.readJsonValues(originalInputStream, jsonFieldPath)
            .plus(newValues)
            .toSortedMap()

    }
}