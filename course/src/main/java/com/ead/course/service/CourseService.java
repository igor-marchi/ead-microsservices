package com.ead.course.service;

import com.ead.course.entity.Course;
import com.ead.course.entity.Lesson;
import com.ead.course.entity.Module;
import com.ead.course.repository.CourseRepository;
import com.ead.course.repository.LessonRepository;
import com.ead.course.repository.ModuleRepository;
import com.ead.course.service.interfaces.ICourseService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService implements ICourseService {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    ModuleRepository moduleRepository;

    @Autowired
    LessonRepository lessonRepository;

    @Transactional
    @Override
    public void delete(Course course) {
        List<Module> moduleList = moduleRepository.findAllByCourse(course.getId());
        if (moduleList.isEmpty()) return;
        for (Module module : moduleList) {
            List<Lesson> lessonList = lessonRepository.findAllByModule(module.getId());
            if (lessonList.isEmpty()) continue;
            lessonRepository.deleteAll(lessonList);
        }
        moduleRepository.deleteAll(moduleList);
        courseRepository.delete(course);
    }
}
