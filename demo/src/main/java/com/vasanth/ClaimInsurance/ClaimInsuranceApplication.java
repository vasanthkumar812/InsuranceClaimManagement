package com.vasanth.ClaimInsurance;


import com.vasanth.ClaimInsurance.Entity.Claim;
import com.vasanth.ClaimInsurance.Repository.ClaimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication(scanBasePackages = {"com.vasanth.ClaimInsurance"}) // Scan the root package

public class ClaimInsuranceApplication implements CommandLineRunner {

	@Autowired
	private ClaimRepository repo;

	public static void main(String[] args) {
		SpringApplication.run(ClaimInsuranceApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		Date claimDate = java.sql.Date.valueOf("2001-12-11");

		Claim claim = new Claim();
		claim.setPolicyId("12345");
		claim.setClaimAmount(100);
		claim.setClaimDate(claimDate);
		claim.setClaimStatus(Claim.ClaimStatus.APPROVED);
		claim.setAdjusterId(123);

		repo.save(claim);

		System.out.println("Record added.");
	}
}