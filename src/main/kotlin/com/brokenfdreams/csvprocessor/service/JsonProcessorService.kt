package com.brokenfdreams.csvprocessor.service

import com.brokenfdreams.csvprocessor.model.Status
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

    fun compare(
        firstInputStream: InputStream,
        secondInputStream: InputStream,
        jsonFieldPath: List<String>
    ): Map<String, Status> {
        val firstJsonValues = jsonReaderService.readJsonValues(firstInputStream, jsonFieldPath).keys
        val secondJsonValues = jsonReaderService.readJsonValues(secondInputStream, jsonFieldPath).keys
        val right = firstJsonValues.minus(secondJsonValues).associateWith { Status.MISSING_RIGHT }
        val left = secondJsonValues.minus(firstJsonValues).associateWith { Status.MISSING_LEFT }
        return right.plus(left)
    }
}