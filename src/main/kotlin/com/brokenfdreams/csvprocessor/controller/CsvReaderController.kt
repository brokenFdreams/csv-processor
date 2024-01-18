package com.brokenfdreams.csvprocessor.controller

import com.brokenfdreams.csvprocessor.service.ComparatorService
import com.brokenfdreams.csvprocessor.service.CsvReaderService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@Tag(name = "CsvReader", description = "Reads csv file and process it")
@RestController("read")
class CsvReaderController(
    private val csvReaderService: CsvReaderService,
    private val comparatorService: ComparatorService
) {

    @Operation(description = "Count entry of each value for given header name")
    @PostMapping("count-by-header", consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun countByField(
        @RequestPart file: MultipartFile,
        @RequestPart headerName: String
    ): ResponseEntity<Map<String, Int>> {
        return ResponseEntity.ok(csvReaderService.readerWithCount(file.inputStream, headerName))
    }

    @Operation(description = "Groups rows by given header and returns specified header values")
    @PostMapping("group-by-header", consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun csvReaderWithGrouping(
        @RequestPart file: MultipartFile,
        @RequestPart headerNameToGroupBy: String,
        @RequestParam headersToReturn: List<String> //RequestPart doesn't work with swagger
    ): ResponseEntity<Map<String, List<List<String>>>> {
        return ResponseEntity.ok(
            csvReaderService.readWithGrouping(
                file.inputStream, headerNameToGroupBy, headersToReturn
            )
        )
    }

    @PostMapping("compare-csv-to-json", consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun compareCsvToJson(
        @RequestPart csv: MultipartFile,
        @RequestPart json: MultipartFile,
        @RequestParam jsonFieldPath: List<String>,
        @RequestParam csvFieldName: String
    ): ResponseEntity<String> {
        return ResponseEntity.ok(
            comparatorService.compareCsvToJsonField(csv.inputStream, csvFieldName, json.inputStream, jsonFieldPath)
        )
    }
}
