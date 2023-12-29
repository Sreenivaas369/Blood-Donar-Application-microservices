package com.in.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.in.Entity.Address;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface AddressRepository extends JpaRepository<Address, Integer> {


    Address findByCity(String city);
    List<Address> findByCountry(String country);


    @Query(value = "select distinct(country) from Address", nativeQuery = true)
    List<String> getCountryList();

    @Query(value = "select distinct(state) from Address where country=:country", nativeQuery = true)
    List<String> findCountry(String country);

    @Query(value = "select distinct(district) from Address where state=:state", nativeQuery = true)
    List<String> findState(String state);

    @Query(value = "select distinct(city) from Address where district=:district", nativeQuery = true)
    List<String> findDistrict(String district);



    // For Registration

}