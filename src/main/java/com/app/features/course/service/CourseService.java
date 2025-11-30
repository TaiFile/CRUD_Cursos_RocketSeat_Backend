package com.app.features.course.service;


import com.app.features.course.dtos.CourseRequestDTO;
import com.app.features.course.dtos.CourseResponseDTO;
import com.app.features.course.exception.CourseNotFoundException;
import com.app.features.course.model.Course;
import com.app.features.course.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class CourseService {
    @Autowired
    private final CourseRepository repository;

    public CourseService(CourseRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public CourseResponseDTO save(CourseRequestDTO courseRequestDTO){
        Course course = new Course();
        course.setName(courseRequestDTO.name());
        course.setCategory(courseRequestDTO.category());
        course.setCreatedAt(LocalDateTime.now());
        repository.save(course);
        return new CourseResponseDTO(course);
    }

    @Transactional(readOnly = true)
    public List<CourseResponseDTO> list(){
        List<Course> courses = repository.findAll();
        return courses.stream()
                .map(CourseResponseDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public CourseResponseDTO findById(UUID id){
        Course course = repository.findById(id).orElseThrow(
                () -> new CourseNotFoundException("Course not found")
        );
        return new CourseResponseDTO(course);
    }

    @Transactional
    public CourseResponseDTO update (UUID id, CourseRequestDTO courseRequestDTO){
        Course course = repository.findById(id).orElseThrow(() -> new CourseNotFoundException("Course not found"));
        course.setName(courseRequestDTO.name());
        course.setCategory(courseRequestDTO.category());
        repository.save(course);
        return new CourseResponseDTO(course);
    }

    public void delete(UUID id){
        Course course = repository.findById(id).orElseThrow(
                () -> new CourseNotFoundException("Course not exists")
        );
        repository.deleteById(id);
    }
}
