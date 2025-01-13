package com.mircro.Mirco1.hostelService;

import com.mircro.Mirco1.Entity.Hostel;
import com.mircro.Mirco1.HostelRepository.hostelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HostelService {

    @Autowired
    private hostelRepository hostelRepository;

    public Hostel saveHostel(Hostel hostel){
        return hostelRepository.save(hostel);

    }

    public List<Hostel> getAllHostel(){
        return hostelRepository.findAll();
    }

    public Hostel getSingleHostel(int hostelid){
        return hostelRepository.findById(hostelid).orElseThrow(()->new RuntimeException("No id found"));
    }

    public List<Hostel> getHostelByStudId(int studId){
        return hostelRepository.findByStudentId(studId);
    }
}
