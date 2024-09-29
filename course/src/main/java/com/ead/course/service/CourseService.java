package com.ead.course.service;

import com.ead.course.repository.CourseRepository;
import com.ead.course.service.interfaces.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService implements ICourseService {

    @Autowired
    CourseRepository courseRepository;
}
