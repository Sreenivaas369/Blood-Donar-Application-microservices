package com.in.Model;

import lombok.*;
import java.io.Serializable;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

	private String userid;

	private String password;

	private String date;
}
