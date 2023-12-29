package com.in.ExternalService;


import com.in.Model.Address;
import jakarta.validation.Valid;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name="ADDRESS-SERVICE")
public interface FeignAddressService {


    @PostMapping("/saveAddress")
    Address SaveAddressFeign(@Valid @RequestBody Address address);

    @GetMapping("/getAddress")
    List<Address>  getAddressFeign(@RequestParam String country);

    @GetMapping("/getAddressCity")
    Address getAddressCityFeign(@RequestParam String city);

    @GetMapping("/getAddressById")
    Address getAddressByIdFeign(@RequestParam int addressId);

}
