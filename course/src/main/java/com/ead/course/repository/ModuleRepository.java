package com.ead.course.repository;

import com.ead.course.entity.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ModuleRepository extends JpaRepository<Module, UUID> {

    @Query(value = "select * from modules where course_id = :courseId", nativeQuery = true)
    List<Module> findAllByCourse(@Param("courseId") UUID courseId);
}
