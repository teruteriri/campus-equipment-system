package edu.cit.carao.estellefelicity.campusequipmentloan.controller;

import edu.cit.carao.estellefelicity.campusequipmentloan.entity.LoanEntity;
import edu.cit.carao.estellefelicity.campusequipmentloan.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @PostMapping
    public LoanEntity createLoan(@RequestBody Map<String, Object> request) {
        Long equipmentId = ((Number) request.get("equipmentId")).longValue();
        String studentNo = (String) request.get("studentNo");
        return loanService.createLoan(equipmentId, studentNo);
    }

    @PostMapping("/{id}/return")
    public LoanEntity returnLoan(@PathVariable Long id) {
        return loanService.returnLoan(id);
    }
}