package com.example.demo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.demo.StringConverter;


@SpringBootApplication
public class CardPaymentSystemApplication {

	public static void main(String[] args) {
				
		
		String amt = "500000";
		String celAmt = "500000";
		
		BigDecimal bAmt = new BigDecimal(amt);
		BigDecimal bCelAmt = new BigDecimal(celAmt);
		
		if(bAmt.compareTo(bCelAmt) >= 0)
		{
			System.out.println("1231233332");
		}
		
				
		
		SpringApplication.run(CardPaymentSystemApplication.class, args);
		
	}

}
