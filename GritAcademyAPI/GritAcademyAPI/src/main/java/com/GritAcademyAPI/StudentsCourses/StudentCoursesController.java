package com.GritAcademyAPI.StudentsCourses;

import com.GritAcademyAPI.Courses.CoursesService;
import com.GritAcademyAPI.Students.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentCoursesController {
    @Autowired
    private StudentCoursesService studentsCoursesService;

    @GetMapping(value = "/studentscourses", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StudentCoursesDTO>> getAllStudentCourses() {
        List<StudentCoursesDTO> studentCoursesList = studentsCoursesService.getStudentsCourses();
        return ResponseEntity.ok(studentCoursesList);
    }
}
