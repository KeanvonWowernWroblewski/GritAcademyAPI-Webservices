package com.GritAcademyAPI.StudentsCourses;


import com.GritAcademyAPI.Students.StudentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentCoursesRepository extends JpaRepository<StudentCoursesEntity, Long> {
}
