package com.GritAcademyAPI.StudentsCourses;

import com.GritAcademyAPI.Courses.CoursesEntity;
import com.GritAcademyAPI.Students.StudentsEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "students_courses")
@Table(name = "students_courses")

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentCoursesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="students_id",
            referencedColumnName = "id")
    private StudentsEntity students;

    @ManyToOne
    @JoinColumn(name = "courses_id",
            referencedColumnName = "Id")
    private CoursesEntity courses;
}
