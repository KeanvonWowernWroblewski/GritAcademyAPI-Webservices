package com.GritAcademyAPI.StudentsCourses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentCoursesService {
    @Autowired
    StudentCoursesRepository studentsCoursesRepository;

    public List<StudentCoursesDTO> getStudentsCourses(){
        List<StudentCoursesDTO> studentsCoursesDTO = new ArrayList<>();
        studentsCoursesRepository.findAll().forEach(studentsCourses ->
                studentsCoursesDTO.add(this.mapToDTO(studentsCourses))
        );
        return studentsCoursesDTO;
    }

    private StudentCoursesDTO mapToDTO(StudentCoursesEntity studentsCourses){
        StudentCoursesDTO dto = new StudentCoursesDTO();
        dto.setId(studentsCourses.getId());
        dto.setStudents_id(studentsCourses.getStudents().getId());
        dto.setCourses_id(studentsCourses.getCourses().getId());
        return dto;
    }
}
