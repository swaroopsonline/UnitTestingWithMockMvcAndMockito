package com.swa.HrmsApplication.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Address {
	
	private String city;
	private String street;
	private String country;
	

}
