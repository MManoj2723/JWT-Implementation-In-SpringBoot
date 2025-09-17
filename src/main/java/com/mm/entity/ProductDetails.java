package com.mm.entity;

import org.springframework.boot.CommandLineRunner;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetails {
	
	private Integer pid;
	
	private String pname;
	
	private Double pprice;
	
	

}
