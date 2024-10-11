package com.ead.course.controller;

import com.ead.course.controller.dto.CourseDto;
import com.ead.course.entity.Course;
import com.ead.course.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@RestController
@RequestMapping("/courses")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CourseController {

    @Autowired
    CourseService courseService;

    @GetMapping
    public ResponseEntity<Object> getAllCourses() {
        return ResponseEntity.status(HttpStatus.OK).body(courseService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCourseById(@PathVariable UUID id) {
        var course = courseService.findById(id);
        if (course.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course not found.");
        return ResponseEntity.status(HttpStatus.OK).body(course.get());
    }

    @PostMapping
    public ResponseEntity<Object> saveCourse(@RequestBody @Valid CourseDto courseDto) {
        var course = new Course();
        BeanUtils.copyProperties(courseDto, course);
        course.setCreatedAt(LocalDateTime.now(ZoneId.of("UTC")));
        course.setUpdatedAt(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.save(course));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCourse(@PathVariable UUID id) {
        var course = courseService.findById(id);
        if (course.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course not found.");
        courseService.delete(course.get());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Course deleted.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCourse(@PathVariable UUID id, @RequestBody @Valid CourseDto courseDto) {
        var course = courseService.findById(id);
        if (course.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course not found.");
        var courseToUpdate = course.get();
        BeanUtils.copyProperties(courseDto, courseToUpdate);
        courseToUpdate.setUpdatedAt(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.OK).body(courseService.save(courseToUpdate));
    }
}
