package com.GritAcademyAPI.Students;

import com.GritAcademyAPI.Courses.CoursesDTO;
import lombok.*;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentsDTO {



    private Long id;

    private String fName;

    private String lName;

    private String town;

    List<CoursesDTO> coursesDTOS = new ArrayList<>();



}