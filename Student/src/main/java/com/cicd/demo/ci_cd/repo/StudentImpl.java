package com.cicd.demo.ci_cd.repo;

import com.cicd.demo.ci_cd.Exception.StudentException;
import com.cicd.demo.ci_cd.enitity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class StudentImpl {

    @Autowired
    private StudentRepo studentRepo;

    public Student setStudent(Student st){
        Optional<Student> studById = studentRepo.findByStudentRollNumber(st.getStudentRollNumber());
        if(studById.isPresent()){
            throw new StudentException("Student with ID already exists");
        }else {
            Student saveSt = studentRepo.save(st);
            return saveSt;
        }
    }

    public Student getStudentById(int id){
        Student stud1 = studentRepo.findById(id).orElseThrow(() -> new StudentException("Student id not found"));
        return stud1;
    }

    public List<Student> getAllStudents(){
        List<Student> allSt = studentRepo.findAll();
        return allSt;
    }

    public Student updateStudent(int id,Student oldStu){
        Student student = studentRepo.findById(id).orElseThrow(()->new StudentException("Student Not Found"));

            student.setStudentName(oldStu.getStudentName());
            student.setStudentRollNumber(oldStu.getStudentRollNumber());
            student.setStudentEmail(oldStu.getStudentEmail());
            student.setStudentBooks(oldStu.getStudentBooks());
            Student savedStu = studentRepo.save(student);
            return savedStu;
    }

  //    Patch Mapping
    public Student updatePartialStudent(int id, Map<String,Object> oldStu){
        Student student = studentRepo.findById(id).orElseThrow(()->new StudentException("Student Not Found"));

        oldStu.forEach((key,value)->{
            switch (key) {
                case "studentname":
                    student.setStudentName((String) value);
                    break;
                case "studentrollnumber":
                    student.setStudentRollNumber((int) value);
                    break;
                case "studentemail":
                    student.setStudentEmail((String) value);
                    break;
                case "studentbooks":
                    student.setStudentBooks((List<String>) value);
                    break;
                default:
                    throw new IllegalArgumentException("Field " + key + " is not valid for partial updates");
            }
        });
        return studentRepo.save(student);
    }

    public String deleteStudent(int id){
        Student stu = studentRepo.findById(id).orElseThrow(()->new StudentException("Student Not Found"));
        studentRepo.delete(stu);
        return "Student Deleted";
    }

    public List<Student> findStudentsByHostelId(int hostelId){
        return studentRepo.findByHostelId(hostelId);
    }
}
