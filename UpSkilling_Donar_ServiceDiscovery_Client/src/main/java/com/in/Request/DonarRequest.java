package com.in.Request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DonarRequest {
	@NotNull(message="Name should not be contain Empty")
	private String fullname;
	@NotBlank(message="Please Mention Blood Group")
	private String bloodgroup;
	@NotBlank(message="Please Select country")
	private String country;
	@NotBlank(message="Please select state")
	private String state;
	@NotBlank(message="Please select district")
	private String district;
	@Column(nullable=false)
	@NotNull(message="Mobilenumber should not be contain Empty")
	//@Size(min=10,max=10,message="Mobile Number should contain 10 digits only and Don't use +91 or 0")
	private long mobilenumber;
	@NotBlank(message="Please select the city")
	private String city;
	@Email
	@NotNull(message="userid id is madatory field")
	private String userid;
	@NotNull(message="Password should be mandatory")
//	@Min(value=8,message="Password should be greater than or equal 8")
//	@Max(value=12,message="Password should be less than or equal 12")
	private String password;
	@NotNull(message="Confirm Password should be mandatory")
	private String confirmPassword;
	@Column(nullable=false)
	@NotNull(message="Please mention are you ready to donate or not")
	private boolean availability;
	private boolean acknowlegement;
	private String date;
	
	

}
