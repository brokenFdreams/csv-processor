package com.brokenfdreams.csvprocessor.service

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Service
import java.io.InputStream

@Service
class JsonReaderService(private val objectMapper: ObjectMapper) {

    fun readJsonValues(json: InputStream, jsonFieldPath: List<String>): Map<String, String> {
        var jsonNode = objectMapper.readTree(json)
        jsonFieldPath.forEach { jsonNode = jsonNode[it] }
        return objectMapper.convertValue(jsonNode, object : TypeReference<HashMap<String, String>>() {})
    }
}
