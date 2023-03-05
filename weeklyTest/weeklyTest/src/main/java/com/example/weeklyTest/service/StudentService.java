package com.example.weeklyTest.service;

import com.example.weeklyTest.dao.StudentRepo;
import com.example.weeklyTest.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    StudentRepo studentRepo;

    public int saveStudent(Student studentObj) {
        Student student=studentRepo.save(studentObj);
        return student.getStudentId();

    }

    public List<Student> getStudent(String studentId) {
        List<Student> students=new ArrayList<>();
        if(studentId!=null && studentRepo.findById(Integer.valueOf(studentId)).isPresent()){
            students.add(studentRepo.findById(Integer.valueOf(studentId)).get());
        }else{
            List<Student> student1=studentRepo.getAll();
            for(Student st: student1){
                students.add(st);
            }
        }
        return students;
    }

    public List<Student> getStudentByFirstName(String firstName) {
        List<Student> st=studentRepo.findByFirstName(firstName);
        return st;
    }

    public List<Student> getStudentByLastName(String lastName) {
        List<Student> st=studentRepo.findByLastName(lastName);
        return st;
    }

    public List<Student> getStudentByAge(String age) {
        List<Student> st=studentRepo.findByAge(age);
        return st;
    }

    public List<Student> getByGreaterAge(String age) {
        List<Student> st=studentRepo.findGreaterByAge(age);
        return st;
    }

}
