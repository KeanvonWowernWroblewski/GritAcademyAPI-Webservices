package com.GritAcademyAPI.Students;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.GritAcademyAPI.Courses.CoursesEntity;

import java.util.List;
@Repository
public interface StudentsRepository extends JpaRepository<StudentsEntity, Long> {
    List<StudentsEntity> findByfName(String fname);
    List<StudentsEntity> findBylName(String lname);
    List<StudentsEntity> findBytown(String town);


    List<StudentsEntity> findByfNameContaining(String string);

    List<StudentsEntity> findBylNameContaining(String string);


}
