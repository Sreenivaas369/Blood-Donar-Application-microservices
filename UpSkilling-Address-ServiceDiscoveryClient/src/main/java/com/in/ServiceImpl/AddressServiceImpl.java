package com.in.ServiceImpl;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Set;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.in.Entity.Address;
import com.in.Repository.AddressRepository;
import com.in.Service.AddressService;

@Service
@Slf4j
@Transactional
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository aRepo;

    @Override
    public Address saveAddress(Address address) {
        Address checkaddress = aRepo.findByCity(address.getCity());
        if (checkaddress != null) {
            return checkaddress;
        }
        return aRepo.save(address);
    }

    @Override
    @Cacheable(key = "#country",value ="Address")
    public List<Address> getAddress(String country) {
        log.info("From Database in get Country");
        return aRepo.findByCountry(country);
    }

    @Override
    @Cacheable(key = "#city",value ="Address")
    public Address getAddressCity(String city) {
        log.info("From Database in get city");
        return aRepo.findByCity(city);
    }

    @Override
    @Cacheable(key = "#addressId",value ="Address")
    public Address getAddressById(int addressId) {
        log.info("From Database in get addressid");
        return aRepo.findById(addressId).get();
    }


    @Override
    public List<String> getCountryList() {
        return aRepo.getCountryList();
    }

    @Override
    public List<String> getStateList(String country) {
        return aRepo.findCountry(country);
    }

    @Override
    public List<String> getDistrictList(String state) {
        return aRepo.findState(state);
    }

    @Override
    public List<String> getCityList(String district) {
        return aRepo.findDistrict(district);
    }


// For Registration

    public Address AddAddress(String country, String state, String district, String city) {

        Address checkaddress = aRepo.findByCity(city);
        if (checkaddress != null) {
            return checkaddress;
        }
        Address address = new Address();
        address.setCountry(country);
        address.setState(state);
        address.setDistrict(district);
        address.setCity(city);
        return aRepo.save(address);
    }


    //Extra Code
    @Override
    public Address findByCity(String city) {
        return aRepo.findByCity(city);
    }

}
