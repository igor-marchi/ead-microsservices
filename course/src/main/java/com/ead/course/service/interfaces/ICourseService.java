package com.ead.course.service.interfaces;

import com.ead.course.entity.Course;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ICourseService {

    void delete(Course course);

    Course save(Course course);

    Optional<Course> findById(UUID id);

    List<Course> findAll();
}
