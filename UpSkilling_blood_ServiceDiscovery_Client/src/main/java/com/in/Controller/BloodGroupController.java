package com.in.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.in.Entity.BloodGroup;
import com.in.Service.BloodGroupService;

import jakarta.validation.Valid;


@RestController
public class BloodGroupController {

    @Autowired
    private BloodGroupService bService;

    @PostMapping("/AddBloodGroup")
    public ResponseEntity<BloodGroup> saveBlood(@Valid @RequestBody BloodGroup bloodgroup) {
        return new ResponseEntity<>(bService.saveBlood(bloodgroup),HttpStatus.CREATED);
    }

    @GetMapping("/GetBloodGroup")
    public ResponseEntity<BloodGroup> getBloodGroup(@RequestParam String bloodGroup) {
        return new ResponseEntity<>(bService.getBloodGroup(bloodGroup),HttpStatus.OK);
    }

    @GetMapping("/GetBloodGroupId")
    public ResponseEntity<BloodGroup> getBloodGroupById(@RequestParam int bloodGroupId) {
        return new ResponseEntity<>(bService.getBloodGroupById(bloodGroupId),HttpStatus.CREATED);
    }

    @GetMapping("/GetBloodGroups")
    public ResponseEntity<List<String>> getAllBloodGroups() {
        return new ResponseEntity<>(bService.getAllBloodGroups(),HttpStatus.CREATED);

    }



//Extra Code

    @GetMapping("/GetAllBloodGroup")
    public ResponseEntity<List<BloodGroup>> getAllBloodGroup_json() {
        return new ResponseEntity<>(bService.getAllBloodGroup_json(),HttpStatus.OK);
    }



}
