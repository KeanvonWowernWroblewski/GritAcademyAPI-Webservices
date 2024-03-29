package com.GritAcademyAPI.Courses;

import com.GritAcademyAPI.Students.StudentsDTO;
import com.GritAcademyAPI.Students.StudentsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CoursesService {
    @Autowired
    CoursesRepository coursesRepository;

    public List<CoursesDTO> getCourses(){
        List<CoursesDTO> coursesDTO  = new ArrayList<>();
        coursesRepository.findAll().forEach(courses -> coursesDTO.add(this.mapToDTO(courses)));
        return coursesDTO;
    }

    public List<CoursesDTO> coursesById(Long id){
        Optional<CoursesEntity> courses = coursesRepository.findById(id).map(coursesid ->{
            coursesid.getStudents().size();
            return coursesid;
        });
        return courses.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<CoursesDTO> coursesByWordInName(String string){
        List<CoursesDTO> coursesDTO = new ArrayList<>();
        coursesRepository.findBynameContaining(string).forEach(courses -> coursesDTO.add(this.mapToDTO(courses)));
        return coursesDTO;
    }


    public List<CoursesDTO> coursesByWordInDescription(String string){
        List<CoursesDTO> coursesDTO = new ArrayList<>();
        coursesRepository.findBydescriptionContaining(string).forEach(courses -> coursesDTO.add(this.mapToDTO(courses)));
        return coursesDTO;
    }
    public List<CoursesDTO> coursesByName(String name){
        List<CoursesEntity> coursesByName = coursesRepository.findByName(name);
        return coursesByName.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public void addCourse(String name, String description) {
        CoursesEntity newCourse = new CoursesEntity();
        newCourse.setName(name);
        newCourse.setDescription(description);
        coursesRepository.save(newCourse);
    }

    public void removeCourseById(Long id){
        Optional<CoursesEntity> coursesOptional = coursesRepository.findById(id);
        if (coursesOptional.isPresent()){
            CoursesEntity courses = coursesOptional.get();
            coursesRepository.delete(courses);
        } else{
            throw new ResourceNotFoundException("Course with id " + id + " not found.");
        }
    }


    private CoursesDTO mapToDTO(CoursesEntity courses){
        CoursesDTO dto = new CoursesDTO();
        dto.setId(courses.getId());
        dto.setName(courses.getName());
        dto.setDescription(courses.getDescription());
        dto.setStudentsDTO(courses.getStudents().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList()));
        return dto;
    }

    private StudentsDTO mapToDTO(StudentsEntity students){
        StudentsDTO dto = new StudentsDTO();
        dto.setId(students.getId());
        dto.setFName(students.getFName());
        dto.setLName(students.getLName());
        dto.setTown(students.getTown());
        return dto;
    }
}
