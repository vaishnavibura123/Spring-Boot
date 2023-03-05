package com.example.weeklyTest.controller;

import com.example.weeklyTest.model.Student;
import com.example.weeklyTest.service.StudentService;
import jakarta.annotation.Nullable;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/student")
public class StudentController {
    @Autowired
    StudentService studentService;
    @PostMapping(value = "/create")
    public ResponseEntity<String> saveStudent(@RequestBody String student){
        Student studentObj = setStudent(student);
        int studentId=studentService.saveStudent(studentObj);
        return new ResponseEntity<>("Student save user id_"+studentId, HttpStatus.CREATED);

    }

    private Student setStudent(String newStudent) {
        JSONObject json=new JSONObject(newStudent);
        Student student=new Student();

        student.setFirstName(json.getString("firstName"));
        student.setLastName(json.getString("lastName"));
        student.setAge(json.getInt("age"));
        student.setActive(json.getBoolean("active"));
        Timestamp createTime=new Timestamp(System.currentTimeMillis());
        student.setAdmissionDate(createTime);
        return student;
    }
    @GetMapping(value = "/get")
    public ResponseEntity<String> getStudent(@Nullable@RequestParam String studentId){
        List<Student> students= studentService.getStudent(studentId);
        return new ResponseEntity<>(students.toString(), HttpStatus.OK);
    }
    @GetMapping(value = "getByFirst")
    public ResponseEntity<String> getStudentByFirstName(@RequestParam String firstName ){
        List<Student> students=studentService.getStudentByFirstName(firstName);
        return new ResponseEntity<>(students.toString(), HttpStatus.OK);
    }
    @GetMapping(value = "getByLast")
    public ResponseEntity<String> getStudentByLastName(@RequestParam String lastName ){
        List<Student> students=studentService.getStudentByLastName(lastName);
        return new ResponseEntity<>(students.toString(), HttpStatus.OK);
    }
    @GetMapping(value = "/getByAge")
    public ResponseEntity<String > getByAge(@RequestParam String age){
        List<Student> students=studentService.getStudentByAge(age);
        return new ResponseEntity<>(students.toString(), HttpStatus.OK);
    }
    @GetMapping(value = "/getByGreaterAge")
    public ResponseEntity<String> getByGreaterAge(@RequestParam String age){
        List<Student> students=studentService.getByGreaterAge(age);
        return new ResponseEntity<>(students.toString(),HttpStatus.OK);
    }
   
}
