package com.app.features.course.controller;

import com.app.features.course.model.Course;
import com.app.features.course.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.crud.course.dto.CourseRequestDTO;
import com.example.crud.course.dto.CourseResponseDTO;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@Controller
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    private  CourseService courseService;

    @PostMapping("/create")
    public ResponseEntity<CourseResponseDTO> save(@Valid @RequestBody CourseRequestDTO courseRequestDTO){
        CourseResponseDTO course = courseService.create(courseRequestDTO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(course.id())
                .toUri();
        return ResponseEntity.created(uri).body(course);
    }

    @GetMapping("/list")
    public ResponseEntity<List<CourseResponseDTO>> list(){
        List<CourseResponseDTO> courses = courseService.list();
        return ResponseEntity.ok(courses);
    }


}