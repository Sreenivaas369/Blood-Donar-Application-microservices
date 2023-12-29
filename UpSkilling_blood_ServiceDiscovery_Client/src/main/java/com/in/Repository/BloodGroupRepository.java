package com.in.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.in.Entity.BloodGroup;

@Repository
@Transactional
public interface BloodGroupRepository extends JpaRepository<BloodGroup, Integer> {

    BloodGroup findByBloodgroup(String bloodgroup);

    @Query(value = "select bloodgroup from bloodgroup", nativeQuery = true)
    List<String> getAllBloodGroups_json();

}