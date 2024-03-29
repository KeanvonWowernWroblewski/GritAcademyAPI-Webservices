package com.GritAcademyAPI.Students;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentsController {
    @Autowired
    StudentsService studentsService;
    @GetMapping(value = "/students", produces = MediaType.APPLICATION_JSON_VALUE)
    List<StudentsDTO> getStudents(){
        return studentsService.getStudents();
    }
    @GetMapping(value = "/students/findbyid/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StudentsDTO>> studentsById(
            @PathVariable(value = "id") Long id
    ){
        if (id<1) {
            return new ResponseEntity<>(HttpStatus.GONE);
        } else {
            List<StudentsDTO> students = studentsService.studentsById(id);
            return new ResponseEntity<>(students, HttpStatus.OK);
        }
    }
    @GetMapping(value = "/students/findbyfirstname/{fName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StudentsDTO>> studentsByFName(
            @PathVariable(value = "fName") String fName
    ){
        List<StudentsDTO> StudentsDTO = studentsService.studentsByFName(fName);
        if(StudentsDTO.isEmpty()){
            throw new ResourceNotFoundException("404 Student not found.");
        }
        return new ResponseEntity<>(StudentsDTO, HttpStatus.OK);
    }
    @GetMapping(value = "/students/findbylastname/{lName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StudentsDTO>> studentsByLName(
            @PathVariable(value = "lName") String lName
    ){
        List<StudentsDTO> StudentsDTO = studentsService.studentsByLName(lName);
        return new ResponseEntity<>(StudentsDTO, HttpStatus.OK);
    }
    @GetMapping(value = "/students/findbytown/{town}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StudentsDTO>> studentsBytown(
            @PathVariable(value = "town") String town
    ){
        List<StudentsDTO> StudentsDTO = studentsService.studentsBytown(town);
        return new ResponseEntity<>(StudentsDTO, HttpStatus.OK);
    }
    @GetMapping(value = "/students/findbywordinfirstname/{string}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StudentsDTO>> studentscontaining(
            @PathVariable(value = "string") String string
    ){
        List<StudentsDTO> studentsDTO = studentsService.getStudentsByWordInFName(string);
        if(studentsDTO.isEmpty()){
            throw new ResourceNotFoundException("Not Found");
        }
        return new ResponseEntity<>(studentsDTO, HttpStatus.OK);
    }
    @GetMapping(value = "/students/findbywordinlastname/{string}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StudentsDTO>> studentsByWordInLName(
            @PathVariable(value = "string") String string
    ){
        List<StudentsDTO> studentsDTO = studentsService.getStudentsByWordInLName(string);
        if(studentsDTO.isEmpty()){
            throw new ResourceNotFoundException("Not Found");
        }
        return new ResponseEntity<>(studentsDTO, HttpStatus.OK);
    }
    @PostMapping(value = "/students/add/{firstName}/{lastName}/{town}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addStudents(
            @PathVariable("firstName") String firstName,
            @PathVariable("lastName") String lastName,
            @PathVariable("town") String town) {
        studentsService.addStudents(firstName, lastName, town);
        return ResponseEntity.ok("student is added");
    }
    @DeleteMapping(value = "/students/remove/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> removeStudents(
            @PathVariable("id") Long id) {
        studentsService.removeStudentById(id);
        return ResponseEntity.ok("Student " + id + " is removed");
    }

}

