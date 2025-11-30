package com.app.features.course.controller;

import com.app.features.course.dtos.CourseRequestDTO;
import com.app.features.course.dtos.CourseResponseDTO;
import com.app.features.course.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/")
    public ResponseEntity<CourseResponseDTO> create(@Valid @RequestBody CourseRequestDTO courseRequestDTO) {
        CourseResponseDTO course = courseService.save(courseRequestDTO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(course.id())
                .toUri();
        return ResponseEntity.created(uri).body(course);
    }

    @GetMapping("/")
    public ResponseEntity<List<CourseResponseDTO>> list() {
        List<CourseResponseDTO> courses = courseService.list();
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> findById(@PathVariable UUID id) {
        CourseResponseDTO course = courseService.findById(id);
        return ResponseEntity.ok(course);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        courseService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> update(@PathVariable UUID id, @Valid @RequestBody CourseRequestDTO courseRequestDTO) {
        CourseResponseDTO course = courseService.update(id, courseRequestDTO);
        return ResponseEntity.ok(course);
    }
}
