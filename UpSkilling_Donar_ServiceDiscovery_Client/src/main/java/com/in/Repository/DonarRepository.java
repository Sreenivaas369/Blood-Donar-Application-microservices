package com.in.Repository;

import java.util.List;

import com.in.Request.DonarResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.in.Entity.Donar;

@Repository
public interface DonarRepository extends JpaRepository<Donar, Integer> {
    List<Donar> findByBloodgroupid(int bloodgroupid);

    List<Donar> findByBloodgroupidAndAddressid(int bloodgroupid, int addressid);

    List<Donar> findByAddressid(int addressid);

}
