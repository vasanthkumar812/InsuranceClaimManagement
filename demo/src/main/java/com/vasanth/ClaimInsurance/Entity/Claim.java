package com.vasanth.ClaimInsurance.Entity;


import org.springframework.format.annotation.DateTimeFormat;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "Claims")
@Data
public class Claim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "claimId") // Explicit column name
    private Long claimId;

    @Column(name = "policyId") // Explicit column name
    private String policyId;

    @Column(name = "claimAmount") // Explicit column name
    private int claimAmount;

    @Column(name = "claimDate") // Explicit column name
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date claimDate;

    @Column(name = "claimStatus") // Explicit column name
    @Enumerated(EnumType.STRING) // Important for Enums
    private ClaimStatus claimStatus;

    @Column(name = "adjusterId") // Explicit column name
    private int adjusterId;

    // Enum for claim status
    public enum ClaimStatus {
        PENDING,
        APPROVED,
        REJECTED
    }

    public Long getClaimId() {
        return claimId;
    }

    public void setClaimId(Long claimId) {
        this.claimId = claimId;
    }

    public String getPolicyId() {
        return policyId;
    }

    public void setPolicyId(String policyId) {
        this.policyId = policyId;
    }

    public int getClaimAmount() {
        return claimAmount;
    }

    public void setClaimAmount(int claimAmount) {
        this.claimAmount = claimAmount;
    }

    public Date getClaimDate() {
        return claimDate;
    }

    public void setClaimDate(Date claimDate) {
        this.claimDate = claimDate;
    }

    public ClaimStatus getClaimStatus() {
        return claimStatus;
    }

    public void setClaimStatus(ClaimStatus claimStatus) {
        this.claimStatus = claimStatus;
    }

    public int getAdjusterId() {
        return adjusterId;
    }

    public void setAdjusterId(int adjusterId) {
        this.adjusterId = adjusterId;
    }


    @Override
    public String toString() {
        return "Claim{" +
                "claimId=" + claimId +
                ", policyId='" + policyId + '\'' +
                ", claimAmount=" + claimAmount +
                ", claimDate=" + claimDate +
                ", claimStatus=" + claimStatus +
                ", adjusterId=" + adjusterId +
                '}';
    }
}