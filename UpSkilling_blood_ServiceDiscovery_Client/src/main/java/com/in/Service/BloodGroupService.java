package com.in.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.in.Entity.BloodGroup;

import jakarta.validation.Valid;

public interface BloodGroupService {

    BloodGroup saveBlood(@Valid BloodGroup bloodgroup);

    BloodGroup getBloodGroup(String bloodGroup);

    BloodGroup addBloodGroup(String bloodgroup);

    List<String> getAllBloodGroups();



//Extra code

    List<BloodGroup> getAllBloodGroup_json();


    BloodGroup getBloodGroupById(int bloodGroupId);
}
