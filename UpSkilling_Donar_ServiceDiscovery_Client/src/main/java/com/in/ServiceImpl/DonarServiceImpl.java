package com.in.ServiceImpl;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import com.in.Entity.Donar;
import com.in.Repository.DonarRepository;
import com.in.Service.DonarService;


@Service
public class DonarServiceImpl implements DonarService {

    @Autowired
    private DonarRepository dRepo;

    @Override
    public Donar Registration(Donar donar) {
        return dRepo.save(donar);
    }

    @Override
    //@Cacheable(key = "#bloodgroupid", value = "Donar")
    public List<Donar> findByBloodgroupidAndAddressid(int bloodgroupid, int addressid) {
        return dRepo.findByBloodgroupidAndAddressid(bloodgroupid, addressid);
    }

    @Override
    //@Cacheable(key = "#bloodgroupid", value = "Donar")
    public List<Donar> findByBloodgroupid(int bloodgroupid) {
        return dRepo.findByBloodgroupid(bloodgroupid);
    }

    @Override
    //@Cacheable(key = "#addressid", value = "Donar")
    public List<Donar> findByAddressid(int addressid) {
        return dRepo.findByAddressid(addressid);
    }


}
