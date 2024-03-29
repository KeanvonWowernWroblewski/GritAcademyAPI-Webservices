package com.GritAcademyAPI.Students;

import com.GritAcademyAPI.Courses.CoursesEntity;
import com.GritAcademyAPI.Courses.CoursesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentsService {
    @Autowired
    StudentsRepository studentsRepository;

    public List<StudentsDTO> getStudents(){
        List<StudentsDTO> studentsDTO = new ArrayList<>();
        studentsRepository.findAll().forEach(students -> studentsDTO.add(this.mapToDTO(students)));
        return studentsDTO;
    }
    public List<StudentsDTO> getStudentsByWordInFName(String string){
        List<StudentsDTO> studentsDTO = new ArrayList<>();
        studentsRepository.findByfNameContaining(string).forEach(students -> studentsDTO.add(this.mapToDTO(students)));
        return studentsDTO;
    }
    public List<StudentsDTO> getStudentsByWordInLName(String string) {
        List<StudentsDTO> studentsDTO = new ArrayList<>();
        studentsRepository.findBylNameContaining(string).forEach(students -> studentsDTO.add(this.mapToDTO(students)));
        return studentsDTO;
    }
    public List<StudentsDTO> studentsById(Long id){
        List<StudentsDTO> studentsDTO = new ArrayList<>();
        studentsRepository.findById(id).map(students
                        -> studentsDTO.add(this.mapToDTO(students)))
                .orElseThrow(() -> new ResourceNotFoundException("404 user not found with id " + id));
        return studentsDTO;
    }
    public List<StudentsDTO> studentsByFName(String fname){
        List<StudentsEntity> studentsbyfname = studentsRepository.findByfName(fname);
        return studentsbyfname.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
    public List<StudentsDTO> studentsByLName(String lname){
        List<StudentsEntity> studentsbylname = studentsRepository.findBylName(lname);
        return studentsbylname.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
    public List<StudentsDTO> studentsBytown(String town){
        List<StudentsEntity> studentsbytown = studentsRepository.findBytown(town);
        return studentsbytown.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
    public void addStudents(String firstName, String lastName, String theirTowns) {
        StudentsEntity morestudent = new StudentsEntity();
        morestudent.setFName(firstName);
        morestudent.setLName(lastName);
        morestudent.setTown(theirTowns);
        studentsRepository.save(morestudent);
    }
    public void removeStudentById(Long id){
        Optional<StudentsEntity> studentsOptional = studentsRepository.findById(id);
        if (studentsOptional.isPresent()){
            StudentsEntity students = studentsOptional.get();
            studentsRepository.delete(students);
        } else{
            throw new ResourceNotFoundException("Student with id " + id + " not found.");
        }
    }
    private StudentsDTO mapToDTO(StudentsEntity students) {
        StudentsDTO dto = new StudentsDTO();
        dto.setId(students.getId());
        dto.setFName(students.getFName());
        dto.setLName(students.getLName());
        dto.setTown(students.getTown());
        dto.setCoursesDTOS(students.getCourses().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList()));
        return dto;
    }
    private CoursesDTO mapToDTO(CoursesEntity courses){
        CoursesDTO dto = new CoursesDTO();
        dto.setId(courses.getId());
        dto.setName(courses.getName());
        dto.setDescription(courses.getDescription());
        return dto;
    }
}