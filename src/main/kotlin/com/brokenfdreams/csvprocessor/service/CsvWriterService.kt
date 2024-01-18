package com.brokenfdreams.csvprocessor.service

import com.github.doyaaaaaken.kotlincsv.client.CsvWriter
import org.springframework.stereotype.Service

@Service
class CsvWriterService(private val csvWriter: CsvWriter) {
    fun writeMap(toWrite: List<List<String>>): String {
        return csvWriter.writeAllAsString(toWrite)
    }

}
