package com.brokenfdreams.csvprocessor.service

import com.github.doyaaaaaken.kotlincsv.client.CsvReader
import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import org.springframework.stereotype.Service
import java.io.InputStream

@Service
class CsvReaderService(private val csvReader: CsvReader) {

    fun csvReaderWithCount(fileInputStream: InputStream, headerNameToCount: String): Map<String, Int> {
        return csvReader.readAllWithHeader(fileInputStream)
            .mapNotNull { it[headerNameToCount] }
            .groupingBy { it }
            .eachCount()
    }

    fun csvReaderWithGrouping(
        fileInputStream: InputStream,
        headerNameToGroupBy: String,
        headersToSave: List<String>
    ): Map<String, List<List<String>>> {
        return csvReader().readAllWithHeader(fileInputStream)
            .map { map ->
                Pair(map[headerNameToGroupBy],
                    map.filter { (header, _) -> headersToSave.contains(header) }
                        .map { it.value }.toList()
                )
            }.groupBy { it.first }
            .filter { it.key != null }
            .map { entry -> Pair(entry.key!!, entry.value.map { it.second }.distinct().toList()) }
            .toMap()
    }
}