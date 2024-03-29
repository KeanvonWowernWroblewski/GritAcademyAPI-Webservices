package com.GritAcademyAPI.Courses;

import com.GritAcademyAPI.Students.StudentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CoursesRepository extends JpaRepository<CoursesEntity, Long> {

    List<CoursesEntity> findByName(String name);

    List<CoursesEntity> findBynameContaining(String string);

    List<CoursesEntity> findBydescriptionContaining(String string);
}
