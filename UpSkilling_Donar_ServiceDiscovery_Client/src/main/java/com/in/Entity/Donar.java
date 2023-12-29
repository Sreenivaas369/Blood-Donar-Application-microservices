package com.in.Entity;

import com.in.Model.Address;
import com.in.Model.BloodGroup;
import com.in.Model.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
//import org.springframework.data.annotation.Transient;

@Entity
@Table(name = "donar")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Donar implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int donarid;
    private String fullname;
    private long mobilenumber;
    private boolean availability;
    private boolean acknowledgement;
    @CreationTimestamp
    private String date;

    private String userid;

    private int addressid;

    private int bloodgroupid;


    @Transient
    private User user;

    @Transient
    private BloodGroup bloodgroup;

    @Transient
    private Address address;

}
