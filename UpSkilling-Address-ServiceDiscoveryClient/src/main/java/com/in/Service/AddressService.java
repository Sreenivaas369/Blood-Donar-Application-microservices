package com.in.Service;

import java.util.List;
import java.util.Set;

import com.in.Entity.Address;
import jakarta.validation.Valid;


public interface AddressService {

    Address findByCity(String city);

    Address saveAddress(@Valid Address address);

    List<Address> getAddress(String country);

    Address getAddressCity(String city);

    Address getAddressById(int addressId);



    List<String> getCountryList();

    List<String> getStateList(String country);

    List<String> getDistrictList(String state);

    List<String> getCityList(String district);


    //For Registration
    Address AddAddress(String country, String state, String district, String city);




//Extra Code



}
