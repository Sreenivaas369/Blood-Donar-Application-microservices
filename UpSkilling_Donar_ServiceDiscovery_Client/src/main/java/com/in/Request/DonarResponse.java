package com.in.Request;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName="build")
public class DonarResponse {

	private String fullname;
	private String bloodgroup;
	private long mobilenumber;
	@Column(nullable=true)
	private String userid;
	private boolean availability;
	private String country;
	private String state;
	private String district;
	private String city;
	private String date;

}
