package com.in.Model;

import lombok.*;

import java.io.Serializable;


//@Entity
//@Table(name="bloodgroup")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BloodGroup implements Serializable {

//    @Id
//    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int bloodgroupid;
//    @Column(unique=true)
//    @NotBlank(message="Blood group should be mandatory")
    private String bloodgroup;

}
