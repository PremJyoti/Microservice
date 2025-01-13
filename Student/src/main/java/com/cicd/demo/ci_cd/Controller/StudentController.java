package com.cicd.demo.ci_cd.Controller;

import com.cicd.demo.ci_cd.Exception.StudentException;
import com.cicd.demo.ci_cd.enitity.Student;
import com.cicd.demo.ci_cd.repo.HostelClient;
import com.cicd.demo.ci_cd.repo.StudentImpl;
import com.cicd.demo.ci_cd.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentImpl studentImpl;

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private HostelClient hostelClient;


//    API for saving data for student
    @PostMapping
    public Student saveStudent(@RequestBody Student student){
        Student student1 = studentImpl.setStudent(student);
        return student1;
    }

//    API for get list of all students including hostel details, from a call to other service
    @GetMapping
    public List<Student> getAllStudents(){
        List<Student> allStudents = studentImpl.getAllStudents();
        List<Student> totalstudents=allStudents.stream().map(student ->{
            student.setHostels(hostelClient.getHostelsByStudID(student.getId()));
            return student;
        }).collect(Collectors.toList());
         return totalstudents;
    }

    //    API for get a single student detail including hostel details, from a call to other service
    @GetMapping("/{studId}")
    public Student getStudentById(@PathVariable  int studId){
        Student studentById = studentImpl.getStudentById(studId);
        studentById.setHostels(hostelClient.getHostelsByStudID(studentById.getId()));
        return studentById;
    }

    //API for updating student using PutMapping
    @PutMapping("/update/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable int id,@RequestBody Student student){
        Student student1 = studentImpl.updateStudent(id, student);
        return ResponseEntity.ok(student1);
    }


    //API for updating student using Patch Mapping
    @PatchMapping("/updatePartial/{id}")
    public ResponseEntity<Student> updatePartialStudent(@PathVariable int id,@RequestBody Map<String,Object> student){
        try{
            Student student1=studentImpl.updatePartialStudent(id,student);
            return ResponseEntity.ok(student1);
        }
        catch (StudentException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }



    //API for deleting a student
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable int id){
        String s = studentImpl.deleteStudent(id);
        return "Deleted student of id->"+id;
    }

    @GetMapping("/hostel/{hostelId}")
    public List<Student> getStudentsByHostelId(@PathVariable int hostelId){
        return studentImpl.findStudentsByHostelId(hostelId);
    }



}
