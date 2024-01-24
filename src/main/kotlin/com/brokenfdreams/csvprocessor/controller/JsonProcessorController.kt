package com.brokenfdreams.csvprocessor.controller

import com.brokenfdreams.csvprocessor.model.Status
import com.brokenfdreams.csvprocessor.service.JsonProcessorService
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile


@Tag(name = "JsonProcessor", description = "Process json")
@RestController("process/json")
class JsonProcessorController(private val jsonProcessorService: JsonProcessorService) {

    @PostMapping("add-new-values", consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun addNewValues(
        @RequestPart originalJson: MultipartFile,
        @RequestPart newValues: MultipartFile,
        @RequestParam jsonFieldPath: List<String>
    ): ResponseEntity<Map<String, String>> {
        return ResponseEntity.ok(
            jsonProcessorService.addNewValues(
                originalJson.inputStream,
                newValues.inputStream,
                jsonFieldPath
            )
        )
    }

    @PostMapping("compare", consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun compareTwoFiles(
        @RequestPart firstJsonFile: MultipartFile,
        @RequestPart secondJsonFile: MultipartFile,
        @RequestParam jsonFieldPath: List<String>
    ): ResponseEntity<Map<String, Status>> {
        return ResponseEntity.ok(
            jsonProcessorService.compare(
                firstJsonFile.inputStream,
                secondJsonFile.inputStream,
                jsonFieldPath
            )
        )
    }
}
