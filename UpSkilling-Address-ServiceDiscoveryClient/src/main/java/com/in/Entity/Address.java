package com.in.Entity;


import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;


@Entity
@Table(name = "Address")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int addressid;
    @NotBlank(message="Please select the country")
    private String country;
    @NotBlank(message="Please select the state")
    private String state;
    @NotBlank(message="Please select the district")
    private String district;
    @NotBlank(message="Please select the city")
    private String city;
    @UpdateTimestamp
    private String date;

}
