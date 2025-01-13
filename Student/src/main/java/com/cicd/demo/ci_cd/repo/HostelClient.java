package com.cicd.demo.ci_cd.repo;

import com.cicd.demo.ci_cd.enitity.Hostel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

//@FeignClient(url = "http://localhost:8082",value = "Hostel-Client")

@FeignClient(name = "HOSTEL-SERVICE")
public interface HostelClient {

    @GetMapping("/hostel/student/{studId}")
    List<Hostel> getHostelsByStudID(@PathVariable int studId);
}
