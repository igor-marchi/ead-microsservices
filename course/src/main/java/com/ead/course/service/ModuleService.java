package com.ead.course.service;

import com.ead.course.repository.ModuleRepository;
import com.ead.course.service.interfaces.IModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModuleService implements IModuleService {

    @Autowired
    ModuleRepository moduleRepository;
}
