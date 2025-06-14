package com.vasanth.ClaimInsurance.Repository;

import com.vasanth.ClaimInsurance.Entity.Claim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, Long> {
    List<Claim> findByPolicyId(String policyId);
}
