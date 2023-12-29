package com.in.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Setter
@Getter
@AllArgsConstructor
public class Address implements Serializable {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int addressid;
 //   @NotBlank(message="Please select the country")
    private String country;
 //   @NotBlank(message="Please select the state")
    private String state;
 //   @NotBlank(message="Please select the district")
    private String district;
//    @NotBlank(message="Please select the city")
    private String city;
//    @UpdateTimestamp
    private String date;


}
