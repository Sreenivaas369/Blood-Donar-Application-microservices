package com.in.Entity;

import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

@Entity
@Table(name = "user")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements Serializable {

    @Id
    private String userid;
    @NotBlank(message="password is missing")
    private String password;
    @UpdateTimestamp
    private String date;
}
