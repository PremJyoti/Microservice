package com.mircro.Mirco1.HostelController;

import com.mircro.Mirco1.Entity.Hostel;
import com.mircro.Mirco1.hostelService.HostelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hostel")
public class hostelController {

    @Autowired
    private HostelService hostelService;

    @PostMapping
    public Hostel saveHostels(@RequestBody Hostel hostel){
          return hostelService.saveHostel(hostel);

    }

    @GetMapping
    public List<Hostel> getAllHostels(){
        return hostelService.getAllHostel();
    }

    @GetMapping("/{id}")
    public Hostel getHostelById(@PathVariable int id){
        return hostelService.getSingleHostel(id);
    }

    @GetMapping("/student/{studId}")
    public List<Hostel> getHostelByStudId(@PathVariable int studId){
        return hostelService.getHostelByStudId(studId);
    }

    @GetMapping("/hostelByStudent/{id}")
    public Optional<Hostel> getHostelByStudentId(@PathVariable int id){
        return hostelService.gethostelByStudentId(id);
    }

}
