package com.mm.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mm.entity.ProductDetails;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/products")
public class SecurityOperationsController {
	
	
	
	private List<ProductDetails> pdetails = new ArrayList<>(List.of(
					
					new ProductDetails(1, "Laptop",1000d),
					new ProductDetails(2, "Mobile",2500d),
					new ProductDetails(3, "Charger",8000d)
				
					));
	
	
	
	@GetMapping("/home")
	public List<ProductDetails> getTheData() {
		System.out.println("SecurityOperationsController.getTheData()-start");
		System.out.println("SecurityOperationsController.getTheData()-end");
		return pdetails;
	}
	
	@GetMapping("/home2")
	public List<ProductDetails> getTheData2() {
		System.out.println("SecurityOperationsController.getTheData2()-start");
		System.out.println("SecurityOperationsController.getTheData2()-end");
		return pdetails;
	}
	
	
	
	@PostMapping("/register")
	public String registerTheProduct(@RequestBody ProductDetails pd) {
		System.out.println("SecurityOperationsController.registerTheProduct()-strat");
		pdetails.add(pd);
		System.out.println("SecurityOperationsController.registerTheProduct()-end");
		return "Product Added With id:"+pd.getPid();
	}
	
	
	
	@GetMapping("/getToken")
	public CsrfToken getTheToken(HttpServletRequest req) {
	System.out.println("SecurityOperationsController.getTheToken()");
		return (CsrfToken)req.getAttribute("_csrf");
	}

}




























