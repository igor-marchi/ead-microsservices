package com.ead.course.controller.dto;

import com.ead.course.entity.enums.CourseLevel;
import com.ead.course.entity.enums.CourseStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class CourseDto {

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    private String imageUrl;

    @NotNull
    private CourseStatus status;

    @NotNull
    private UUID userInstructorId;

    @NotNull
    private CourseLevel level;
}
