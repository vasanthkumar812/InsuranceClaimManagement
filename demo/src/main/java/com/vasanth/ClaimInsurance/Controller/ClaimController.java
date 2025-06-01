package com.vasanth.ClaimInsurance.Controller;

import com.vasanth.ClaimInsurance.Entity.Claim;
import com.vasanth.ClaimInsurance.Repository.ClaimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam; // Added for convenience

import java.util.List;
import java.util.Optional;

@Controller
public class ClaimController {

    @Autowired
    private ClaimRepository claimRepository;

    // --- Core Navigation Endpoints ---

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/submitClaim")
    public String showSubmitClaimForm(Model model) {
        model.addAttribute("claimData", new Claim());
        return "submitClaim";
    }

    @GetMapping("/updateClaimStatus")
    public String showUpdateClaimStatusForm(Model model) {
        return "updateClaimStatus";
    }

    @GetMapping("/getClaimDetails")
    public String showGetClaimDetailsForm(Model model) {
        return "getClaimDetails";
    }



    @GetMapping("/getAllClaimsByPolicy")
    public String showGetClaimsByPolicyForm(Model model) {
        return "getAllClaimsByPolicy";
    }



    // --- Action Endpoints (POST requests) ---

    // Process submitting a new claim (POST request)
    @PostMapping("/submit")
    public String submitClaim(@ModelAttribute("claimData") Claim claimData, Model model) {
        claimRepository.save(claimData);
        System.out.println("Record added...");
        model.addAttribute("submitMessage", "Claim submitted successfully! ID: " + claimData.getClaimId());
        model.addAttribute("submittedClaim", claimData); // Pass the saved claim for display
        return "submitClaim"; // Stay on the submit form to show the message
    }

    // Process updating claim status (POST request)
    @PostMapping("/updateStatus")
    public String updateClaimStatus(@ModelAttribute("claim") Claim claim, Model model) {
        Optional<Claim> claimOptional = claimRepository.findById(claim.getClaimId());

        if (claimOptional.isPresent()) {
            Claim existingClaim = claimOptional.get();
            existingClaim.setClaimStatus(claim.getClaimStatus()); // Update only the status
            Claim updatedClaim = claimRepository.save(existingClaim);
            model.addAttribute("updateMessage", "Claim " + updatedClaim.getClaimId() + " status updated to " + updatedClaim.getClaimStatus() + ".");
            model.addAttribute("updatedClaimDetails", updatedClaim); // Pass updated claim details
        } else {
            model.addAttribute("updateError", "Claim with ID " + claim.getClaimId() + " not found. Cannot update status.");
        }
        return "updateClaimStatus"; // Stay on the update status page to show the message
    }

        @GetMapping("/claimDetails")
        public String getClaimDetails(@RequestParam("claimId") Long claimId, Model model) {
            Optional<Claim> optionalClaim = claimRepository.findById(claimId);

            if (optionalClaim.isPresent()) {
                Claim foundClaim = optionalClaim.get();
                model.addAttribute("claimData", foundClaim); // Add the found claim to the model
                return "success"; // Display details on a dedicated result page
            } else {
                model.addAttribute("errorMessage", "Claim with ID " + claimId + " not found.");
                // Return to the form page to display the error
                return "getClaimDetails";
            }
        }

    @GetMapping("/claimsByPolicy")
    public String getClaimsByPolicy(@RequestParam("policyId") String policyId, Model model) {
        if (policyId == null || policyId.trim().isEmpty()) {
            model.addAttribute("listErrorMessage", "Policy ID cannot be empty.");
            return "getAllClaimsByPolicy";
        }

        List<Claim> claims = claimRepository.findByPolicyId(policyId);

        if (!claims.isEmpty()) {
            model.addAttribute("claimsList", claims);
            model.addAttribute("policyId", policyId);
            return "claimsByPolicy"; // Make sure this page exists
        } else {
            model.addAttribute("listErrorMessage", "No claims found for policy ID: " + policyId);
            return "getAllClaimsByPolicy";
        }
    }


}