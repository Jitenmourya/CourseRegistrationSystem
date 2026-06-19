package com.example.course.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/registrations")
public class RegistrationController {

    @Autowired
    private JdbcTemplate jdbc;

    @PostMapping
    public ResponseEntity<?> register(@RequestBody Map<String,Object> body) {
        Integer sid = (Integer) body.get("student_id");
        Integer cid = (Integer) body.get("course_id");
        int updated = jdbc.update("INSERT INTO registration(student_id,course_id) VALUES(?,?)", sid, cid);
        if (updated == 1) return ResponseEntity.ok(Map.of("status","ok"));
        return ResponseEntity.status(500).body(Map.of("status","error"));
    }
}
