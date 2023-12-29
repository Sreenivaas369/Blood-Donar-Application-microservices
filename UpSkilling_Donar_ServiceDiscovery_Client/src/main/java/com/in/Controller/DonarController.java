package com.in.Controller;

import java.util.*;

import com.in.ExceptionHandler.InvalidPasswordException;
import com.in.ExternalService.FeignAddressService;
import com.in.ExternalService.FeignBloodGroupService;
import com.in.ExternalService.FeignUserService;
import com.in.Model.Address;
import com.in.Model.BloodGroup;
import com.in.Model.User;
import com.in.Repository.DonarRepository;
import com.in.Request.DonarRequest;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import com.in.Entity.Donar;

import com.in.Request.DonarResponse;
import com.in.Service.DonarService;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
@Validated
public class DonarController {

    @Autowired
    private DonarService dService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private FeignAddressService feignAddressService;

    @Autowired
    private FeignBloodGroupService feignBloodGroupService;

    @Autowired
    private FeignUserService feignUserService;

    // To Registration of Donar

    @PostMapping("/donar/Registration")
    @HystrixCommand(defaultFallback = "RegistrationFallback")
    public ResponseEntity<Donar> Registration(@Valid @RequestBody DonarRequest donarrequest) throws InvalidPasswordException {
        Donar donar = new Donar();
        if (donarrequest.getPassword().equals(donarrequest.getConfirmPassword())) {
            donar.setFullname(donarrequest.getFullname());
            donar.setMobilenumber(donarrequest.getMobilenumber());
            donar.setAvailability(donarrequest.isAvailability());
            donar.setAcknowledgement(true);
            donar.setUserid(donarrequest.getUserid());

            //load Balancer

            //ServiceInstance blood_serviceInstance = loadBalancerClient.choose("BLOOD-SERVICE");
            //String blood_uri = blood_serviceInstance.getUri().toString();

            // By using rest template

            String blood_uri = "http://BLOOD-SERVICE";
            log.info("{}", blood_uri);
            BloodGroup bloodGroup = restTemplate.postForObject(blood_uri + "/AddBloodGroup", donarrequest, BloodGroup.class);


            // ServiceInstance address_serviceInstance = loadBalancerClient.choose("ADDRESS-SERVICE");
            // String address_url = address_serviceInstance.getUri().toString();
            String address_url = "http://ADDRESS-SERVICE";
            Address address = restTemplate.postForObject(address_url + "/saveAddress", donarrequest, Address.class);

            // ServiceInstance user_serviceInstance = loadBalancerClient.choose("USER-SERVICE");
            // String user_url = user_serviceInstance.getUri().toString();
            String user_url = "http://USER-SERVICE";
            User user = restTemplate.postForObject(user_url + "/saveuser", donarrequest, User.class);

            donar.setAddressid(address.getAddressid());
            donar.setBloodgroupid(bloodGroup.getBloodgroupid());
            donar.setUserid(user.getUserid());

            donar.setUser(user);
            donar.setAddress(address);
            donar.setBloodgroup(bloodGroup);

            return new ResponseEntity<>(dService.Registration(donar), HttpStatus.CREATED);

        } else {
            throw new InvalidPasswordException("Password and Confirm Password Should be Match");
        }

    }

    //To get the Donar by  Country

    @GetMapping("/getDonar/ByCountry")
    @HystrixCommand(fallbackMethod = "fallbackMethod")
    public ResponseEntity<List<DonarResponse>> getDonarByCountry(@RequestParam String country) {

        List<DonarResponse> donarResponse_list = new ArrayList<>();

        // By using RestTemplate

//        final String add_url = "http://ADDRESS-SERVICE/getAddress?country=" + country;
//        ResponseEntity<Address[]> response = restTemplate.getForEntity(add_url, Address[].class);
//        Address[] address = response.getBody();

        // By using feign client

        List<Address> address = feignAddressService.getAddressFeign(country);

        for (Address i : address) {

            List<Donar> donar_list = dService.findByAddressid(i.getAddressid());

            for (Donar donar : donar_list) {

                //    By using RestTemplate

//                final String blood_url = "http://BLOOD-SERVICE/GetBloodGroupId?bloodGroupId=" + donar.getBloodgroupid();
//                ResponseEntity<BloodGroup> blood_response = restTemplate.getForEntity(blood_url, BloodGroup.class);
//                BloodGroup blood = blood_response.getBody();

                //   By using Feign client

                BloodGroup blood = feignBloodGroupService.getBloodGroupByIdFeign(donar.getBloodgroupid());


                if (donar.getBloodgroupid() == blood.getBloodgroupid() && donar.getAddressid() == i.getAddressid()) {
                    DonarResponse donarResponse = new DonarResponse();
                    donarResponse.setFullname(donar.getFullname());
                    donarResponse.setMobilenumber(donar.getMobilenumber());
                    donarResponse.setAvailability(donar.isAvailability());
                    donarResponse.setUserid(donar.getUserid());
                    donarResponse.setCountry(i.getCountry());
                    donarResponse.setState(i.getState());
                    donarResponse.setDistrict(i.getDistrict());
                    donarResponse.setCity(i.getCity());
                    donarResponse.setBloodgroup(blood.getBloodgroup());
                    donarResponse.setDate(donar.getDate());
                    donarResponse_list.add(donarResponse);
                }
            }
            log.info("{}", i);
            log.info("{}", i.getAddressid());
        }
        return new ResponseEntity<>(donarResponse_list, HttpStatus.OK);
    }


    // Getting Donars by using Bloodgroup

    @GetMapping("/getDonar/ByBlood")
    @HystrixCommand(fallbackMethod = "fallbackMethod")
    public ResponseEntity<List<DonarResponse>> getDonarByBloodGroup(@RequestParam(required = true) String bloodGroup) {

        List<DonarResponse> donarResponse_list = new ArrayList<>();

        //By using Rest Template

//        final String blood_url = "http://BLOOD-SERVICE/GetBloodGroup?bloodGroup=" + bloodGroup;
//        ResponseEntity<BloodGroup> blood_response = restTemplate.getForEntity(blood_url, BloodGroup.class);
//        BloodGroup blood = blood_response.getBody();

    //By using feign client

        BloodGroup blood=feignBloodGroupService.getBloodGroupFeign(bloodGroup);

        List<Donar> donar_list = dService.findByBloodgroupid(blood.getBloodgroupid());

        for (Donar donar : donar_list) {

            //By using RestTemplate

//            final String add_url = "http://ADDRESS-SERVICE/getAddressById?addressId=" + donar.getAddressid();
//            ResponseEntity<Address> response = restTemplate.getForEntity(add_url, Address.class);
//            Address address = response.getBody();

            //By using feign client

            Address address=feignAddressService.getAddressByIdFeign(donar.getAddressid());

            if (donar.getBloodgroupid() == blood.getBloodgroupid() && donar.getAddressid() == address.getAddressid()) {
                DonarResponse donarResponse = new DonarResponse();
                donarResponse.setFullname(donar.getFullname());
                donarResponse.setMobilenumber(donar.getMobilenumber());
                donarResponse.setAvailability(donar.isAvailability());
                donarResponse.setUserid(donar.getUserid());
                donarResponse.setCountry(address.getCountry());
                donarResponse.setState(address.getState());
                donarResponse.setDistrict(address.getDistrict());
                donarResponse.setCity(address.getCity());
                donarResponse.setBloodgroup(blood.getBloodgroup());
                donarResponse.setDate(donar.getDate());
                donarResponse_list.add(donarResponse);
            }
        }
        return new ResponseEntity<>(donarResponse_list, HttpStatus.OK);
    }


//To get Donars by  BloodGroup and City

    @GetMapping("/getDonars")
   // @HystrixCommand(fallbackMethod = "fallbackMethod")
    @HystrixCommand(fallbackMethod = "fallbackStringuser")
    public ResponseEntity<List<DonarResponse>> getDonars(@RequestParam(required = false) String country,
                                                         @RequestParam(required = false) String state, @RequestParam(required = false) String district,
                                                         @RequestParam(required = true) String city, @RequestParam(required = true) String bloodgroup) {

        List<DonarResponse> donarResponseList = new ArrayList<>();

    //By using Rest Template

//        final String blood_url = "http://BLOOD-SERVICE/GetBloodGroup?bloodGroup=" + bloodgroup;
//        ResponseEntity<BloodGroup> bloodGroup = restTemplate.getForEntity(blood_url, BloodGroup.class);
//        BloodGroup blood = bloodGroup.getBody();
//
//        final String address_url = "http://ADDRESS-SERVICE/getAddressCity?city=" + city;
//        ResponseEntity<Address> addressData = restTemplate.getForEntity(address_url, Address.class);
//        Address address = addressData.getBody();

    //By using Feign client

        BloodGroup blood=feignBloodGroupService.getBloodGroupFeign(bloodgroup);

        Address address=feignAddressService.getAddressCityFeign(city);


        List<Donar> donar_list = dService.findByBloodgroupidAndAddressid(blood.getBloodgroupid(), address.getAddressid());
        for (Donar donar : donar_list) {
            DonarResponse response = new DonarResponse();
            response.setFullname(donar.getFullname());
            response.setMobilenumber(donar.getMobilenumber());
            response.setAvailability(donar.isAvailability());
            response.setUserid(donar.getUserid());
            response.setCountry(address.getCountry());
            response.setState(address.getState());
            response.setDistrict(address.getDistrict());
            response.setCity(address.getCity());
            response.setBloodgroup(blood.getBloodgroup());
            response.setDate(donar.getDate());
            donarResponseList.add(response);
        }
        return new ResponseEntity<>(donarResponseList, HttpStatus.OK);
    }




    public ResponseEntity<Donar> RegistrationFallback(@Valid @RequestBody DonarRequest donarrequest) throws InvalidPasswordException {
        Donar donar = new Donar();
        if (donarrequest.getPassword().equals(donarrequest.getConfirmPassword())) {
            donar.setFullname(donarrequest.getFullname());
            donar.setMobilenumber(donarrequest.getMobilenumber());
            donar.setAvailability(donarrequest.isAvailability());
            donar.setAcknowledgement(true);
            donar.setUserid(donarrequest.getUserid());

            return new ResponseEntity<>(dService.Registration(donar), HttpStatus.CREATED);

        } else {
            throw new InvalidPasswordException("Password and Confirm Password Should be Match");
        }

    }

    public ResponseEntity<List<DonarResponse>> fallbackMethod(@RequestParam String country) {

        List<DonarResponse> donarResponse_list = new ArrayList<>();

        return new ResponseEntity<>(donarResponse_list, HttpStatus.OK);
    }



}
