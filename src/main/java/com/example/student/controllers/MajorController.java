package com.example.student.controllers;

import com.example.student.models.Major;
import com.example.student.models.Student;
import com.example.student.respon.ResponseData;
import com.example.student.services.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Majors")
public class MajorController {
    private final MajorService majorService;

    @Autowired
    public MajorController(MajorService majorService) {
        this.majorService = majorService;
    }

    @GetMapping
    public ResponseData<List<Major>> getAll() {
        return new ResponseData<>(0,majorService.getAllMajors(), "");
    }

    @GetMapping(path = "{majorId}")
    public ResponseData<Major> getMajorById(@PathVariable("majorId") Long majorId) {
        return new ResponseData<>(0,majorService.getMajorById(majorId), "");
    }

    @PutMapping("/edit")
    public ResponseData<Major> updateMajor(@RequestBody Major major) {
        return new ResponseData<>(0,majorService.updateMajor(major), "");
    }
    @PostMapping("/add")
    public ResponseData<Major> saveMajor(@RequestBody Major major) {
        return new ResponseData<>(0,majorService.saveMajor(major), "");
    }

    @DeleteMapping(path = "delete/{majorId}")
    public void deleteMajor(@PathVariable("majorId") Long majorId) {
        majorService.deleteMajorById(majorId);
    }
}
