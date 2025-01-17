package com.mircro.Mirco1.hostelService;

import com.mircro.Mirco1.Entity.Hostel;
import com.mircro.Mirco1.Exception.CustomException;
import com.mircro.Mirco1.HostelRepository.hostelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostelService {

    @Autowired
    private hostelRepository hostelRepository;

    public Hostel saveHostel(Hostel hostel) {
        Optional<Hostel> hostelByStudentId = hostelRepository.findHostelByStudentId(hostel.getStudentId());
                if(hostelByStudentId.isPresent()){
                    throw new CustomException("Student with associated hostel already present");
                }
                else {
                    return hostelRepository.save(hostel);
                }

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

    public Optional<Hostel> gethostelByStudentId(int id){
        return hostelRepository.findHostelByStudentId(id);
    }
}
