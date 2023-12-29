package com.brokenfdreams.csvprocessor

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CsvProcessor

fun main(args: Array<String>) {
    runApplication<CsvProcessor>(*args)
}
