package com.in.Service;

import com.in.Entity.Donar;
import com.in.Request.DonarResponse;

import java.util.List;


public interface DonarService {

    Donar Registration(Donar donar);

    List<Donar> findByBloodgroupidAndAddressid(int bloodgroupid, int addressid);

    List<Donar> findByBloodgroupid(int bloodgroupid);

    List<Donar> findByAddressid(int addressid);
}
