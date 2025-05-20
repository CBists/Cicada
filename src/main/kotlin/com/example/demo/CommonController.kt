package com.example.demo

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.sql.DriverManager
import java.sql.Statement

@RestController
@RequestMapping("/api/")
@CrossOrigin(origins = ["*"])
class CommonController {

    lateinit var statement: Statement

    constructor() {
        val connection = DriverManager.getConnection(
            "jdbc:h2:mem:test;MODE=MySQL;DB_CLOSE_DELAY=-1",
            "sa",
            ""
        )
        statement = connection.createStatement()
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS users (name VARCHAR(50) NOT NULL, password TEXT) ENGINE=MEMORY")
        statement.executeUpdate("INSERT INTO users (name, password) VALUES ('Kuzma', 'euo23123dew')")
    }

    @GetMapping("main/check")
    fun mainPageCheck(
        @RequestParam answer1: String,
        @RequestParam answer2: String,
        @RequestParam answer3: String,
        @RequestParam answer4: String
    ): ResponseEntity<String?> =
        if (answer1 == "1ca383cd1ee7f63a" && answer2 == "b3d74eea97bfb75c" && answer3 == "26fe1629f0d72bc0" && answer4 == "c29738ef01d2060a") {
            ResponseEntity.ok("Окак, он смог (ответ прямиком с бекенда)")
        } else {
            ResponseEntity.ok("Лох")
        }

    @GetMapping("task4/check")
    fun task4Check(
        @RequestParam name: String,
        @RequestParam lName: String,
        @RequestParam date: String,
    ): ResponseEntity<String?> =
        if (name == "Андрей" && lName == "Толченов") {
            if (date == "01.07.2023") {
                ResponseEntity.ok("4 - c29738ef01d2060a")
            } else if (date == "21.05.2003") {
                ResponseEntity.ok("Обязательно твое др")
            } else {
                ResponseEntity.ok("А вот думай че за дата")
            }
        } else {
            ResponseEntity.ok("Лох")
        }

    @GetMapping("task2/check")
    fun task2Check(
        @RequestParam name: String,
        @RequestParam pass: String,
    ): ResponseEntity<String?> {
        var finalQuery = "SELECT * FROM users WHERE name = '$name' AND password = '$pass'"
        var result = statement.execute(finalQuery)
        if (result) {
            var res = statement.resultSet
            if (res.next())
                return ResponseEntity.ok("ты смог войти в аккаунт. 2 - b3d74eea97bfb75c")
            else
                return ResponseEntity.ok("Лох")
        } else {
            return ResponseEntity.ok("Лох")
        }
    }
}