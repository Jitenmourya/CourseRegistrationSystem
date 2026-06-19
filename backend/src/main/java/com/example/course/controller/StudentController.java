package com.example.course.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private JdbcTemplate jdbc;

    private final RowMapper<Map<String,Object>> mapper = (rs, i) -> Map.of(
            "id", rs.getInt("id"),
            "name", rs.getString("name"),
            "email", rs.getString("email"),
            "department", rs.getString("department")
    );

    @GetMapping
    public List<Map<String,Object>> getAll() {
        return jdbc.query("SELECT * FROM students ORDER BY id", mapper);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Map<String,Object> body) {
        String name = (String) body.get("name");
        String email = (String) body.get("email");
        String dept = (String) body.get("department");
        int updated = jdbc.update("INSERT INTO students(name,email,department) VALUES(?,?,?)", name, email, dept);
        if (updated == 1) return ResponseEntity.ok(Map.of("status","ok"));
        return ResponseEntity.status(500).body(Map.of("status","error"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        int del = jdbc.update("DELETE FROM students WHERE id=?", id);
        if (del == 1) return ResponseEntity.ok(Map.of("status","ok"));
        return ResponseEntity.status(404).body(Map.of("status","not found"));
    }
}
