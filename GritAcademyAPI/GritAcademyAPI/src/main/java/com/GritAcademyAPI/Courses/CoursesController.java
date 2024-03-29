package com.GritAcademyAPI.Courses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CoursesController {
    @Autowired
    CoursesService coursesService;

    @GetMapping(value = "/courses", produces = MediaType.APPLICATION_JSON_VALUE)
    List<CoursesDTO> getCourses(){
        return coursesService.getCourses();
    }
    @GetMapping(value = "/courses/findbyid/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CoursesDTO>> coursesById(
            @PathVariable(value = "id") Long id
    ){
        List<CoursesDTO> coursesDTO = coursesService.coursesById(id);
        return new ResponseEntity<>(coursesDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/courses/findbyname/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CoursesDTO>> coursesByName(
            @PathVariable(value = "name") String name
    ){
        List<CoursesDTO> coursesDTO = coursesService.coursesByName(name);
        return new ResponseEntity<>(coursesDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/courses/findbywordinname/{string}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CoursesDTO>> coursesContaining(
            @PathVariable(value = "string") String string
    ){
        List<CoursesDTO> coursesDTO = coursesService.coursesByWordInName(string);
        if(coursesDTO.isEmpty()){
            throw new ResourceNotFoundException("Not Found");
        }
        return new ResponseEntity<>(coursesDTO, HttpStatus.OK);
    }
    @GetMapping(value = "/courses/findbywordindescription/{string}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CoursesDTO>> coursesdescriptionContaining(
            @PathVariable(value = "string") String string
    ){
        List<CoursesDTO> coursesDTO = coursesService.coursesByWordInDescription(string);
        if(coursesDTO.isEmpty()){
            throw new ResourceNotFoundException("Not Found");
        }
        return new ResponseEntity<>(coursesDTO, HttpStatus.OK);
    }
    @PostMapping(value = "/courses/add/{name}/{description}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addCourse(
            @PathVariable("name") String name,
            @PathVariable("description") String description) {
        coursesService.addCourse(name, description);
        return ResponseEntity.ok("course is added");
    }
    @DeleteMapping(value = "/courses/remove/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> removeCourse(
            @PathVariable("id") Long id) {
        coursesService.removeCourseById(id);
        return ResponseEntity.ok("Course " + id + " is removed");
    }

}