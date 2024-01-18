package com.brokenfdreams.csvprocessor.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import com.github.doyaaaaaken.kotlincsv.dsl.csvWriter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CsvProcessorConfiguration {

    @Bean
    fun getCsvReader() = csvReader()

    @Bean
    fun getCsvWriter() = csvWriter()

    @Bean
    fun getObjectMapper(): ObjectMapper = ObjectMapper().registerModule(JavaTimeModule())
}