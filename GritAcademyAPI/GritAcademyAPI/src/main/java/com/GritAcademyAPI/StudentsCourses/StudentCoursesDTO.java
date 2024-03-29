package com.GritAcademyAPI.StudentsCourses;

import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentCoursesDTO {

    private Long id;

    private Long students_id;

    private Long courses_id;
}
