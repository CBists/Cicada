package com.example.demo

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/")
class CommonController {
    @GetMapping("pokemon")
    fun pokemonDetail(@RequestParam id: String): ResponseEntity<String?> {
        return ResponseEntity.ok<String?>(id)
    }
}