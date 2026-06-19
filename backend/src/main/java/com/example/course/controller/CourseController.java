package com.example.course.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private JdbcTemplate jdbc;

    private final RowMapper<Map<String,Object>> mapper = (rs, i) -> Map.of(
            "id", rs.getInt("id"),
            "course_name", rs.getString("course_name"),
            "credits", rs.getInt("credits")
    );

    @GetMapping
    public List<Map<String,Object>> getAll() {
        return jdbc.query("SELECT * FROM courses ORDER BY id", mapper);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Map<String,Object> body) {
        String name = (String) body.get("course_name");
        Integer credits = (Integer) body.get("credits");
        int updated = jdbc.update("INSERT INTO courses(course_name,credits) VALUES(?,?)", name, credits);
        if (updated == 1) return ResponseEntity.ok(Map.of("status","ok"));
        return ResponseEntity.status(500).body(Map.of("status","error"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        int del = jdbc.update("DELETE FROM courses WHERE id=?", id);
        if (del == 1) return ResponseEntity.ok(Map.of("status","ok"));
        return ResponseEntity.status(404).body(Map.of("status","not found"));
    }
}
