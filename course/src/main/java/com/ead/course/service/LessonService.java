package com.ead.course.service;

import com.ead.course.repository.LessonRepository;
import com.ead.course.service.interfaces.ILessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LessonService implements ILessonService {

    @Autowired
    LessonRepository lessonRepository;
}
