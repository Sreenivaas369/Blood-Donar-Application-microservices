package com.in.ExternalService;

import com.in.Model.BloodGroup;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="BLOOD-SERVICE")
public interface FeignBloodGroupService {

    @PostMapping("/AddBloodGroup")
    BloodGroup saveBloodFeign(@Valid @RequestBody BloodGroup bloodgroup);

    @GetMapping("/GetBloodGroup")
    BloodGroup getBloodGroupFeign(@RequestParam String bloodGroup);

    @GetMapping("/GetBloodGroupId")
    BloodGroup getBloodGroupByIdFeign(@RequestParam int bloodGroupId);

}
