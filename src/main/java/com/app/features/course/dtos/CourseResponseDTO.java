package com.app.features.course.dtos;


import com.app.features.course.model.Course;

import java.util.List;
import java.util.UUID;

public record CourseResponseDTO(
        UUID id,
        String name,
        String category,
        String createdAt
) {
    public CourseResponseDTO(Course course) {
        this(
                course.getId(),
                course.getName(),
                course.getCategory(),
                course.getCreatedAt().toString()
        );
    }
}
