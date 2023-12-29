package com.brokenfdreams.csvprocessor.config

import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CsvProcessorConfiguration {

    @Bean
    fun getCsvReader() = csvReader()
}