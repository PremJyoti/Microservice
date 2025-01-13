package com.cicd.demo.ci_cd.repo;

import com.cicd.demo.ci_cd.enitity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface StudentRepo extends JpaRepository<Student,Integer> {

    List<Student> findByHostelId(int hostelId);
}
