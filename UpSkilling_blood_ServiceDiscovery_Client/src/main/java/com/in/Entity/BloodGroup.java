package com.in.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;


@Entity
@Table(name="bloodgroup")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BloodGroup implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int bloodgroupid;
    @Column(unique=true)
    @NotBlank(message="Blood group should be mandatory")
    private String bloodgroup;

}
