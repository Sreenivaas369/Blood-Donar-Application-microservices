package com.in.ExternalService;

import com.in.Model.User;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="USER-SERVICE")
public interface FeignUserService {

    @PostMapping("/saveuser")
    User Saveuser(@Valid @RequestBody User user);

}
