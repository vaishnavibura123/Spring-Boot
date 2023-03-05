package com.example.weeklyTest.dao;

import com.example.weeklyTest.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {
    @Query(value = "select * from tbl_student where active =1", nativeQuery = true)
    List<Student> getAll();

    List<Student> findByFirstName(String firstName);

    List<Student> findByLastName(String lastName);

    List<Student> findByAge(String age);

    List<Student> findGreaterByAge(String age);

    @Query(value = "select * from tbl_student where first_name =:firstName or last_name= :lastName",nativeQuery = true)
    List<Student> findByFirstNameOrLastName(String fistName, String lastName);
}
