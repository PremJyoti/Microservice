package com.mircro.Mirco1.HostelRepository;

import com.mircro.Mirco1.Entity.Hostel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface hostelRepository extends JpaRepository<Hostel, Integer> {

//    custom finder method for searching hostel via student id
    List<Hostel> findByStudentId(int studentId);
   Optional<Hostel> findHostelByStudentId(int studentId);
}
