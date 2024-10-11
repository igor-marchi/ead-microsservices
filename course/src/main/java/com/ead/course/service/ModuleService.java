package com.ead.course.service;

import com.ead.course.entity.Lesson;
import com.ead.course.entity.Module;
import com.ead.course.repository.LessonRepository;
import com.ead.course.repository.ModuleRepository;
import com.ead.course.service.interfaces.IModuleService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModuleService implements IModuleService {

    @Autowired
    ModuleRepository moduleRepository;

    @Autowired
    LessonRepository lessonRepository;

    @Transactional
    @Override
    public void delete(Module module) {
        List<Lesson> lessonList = lessonRepository.findAllByModule(module.getId());
        if (lessonList.isEmpty()) return;
        lessonRepository.deleteAll(lessonList);
        moduleRepository.delete(module);
    }
}
