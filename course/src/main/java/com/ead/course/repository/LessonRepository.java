package com.ead.course.repository;

import com.ead.course.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface LessonRepository extends JpaRepository<Lesson, UUID> {

    @Query(value = "select * from lessons where module_id = :moduleId", nativeQuery = true)
    List<Lesson> findAllByModule(@Param("moduleId") UUID moduleId);
}
