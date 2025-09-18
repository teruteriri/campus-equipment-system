package edu.cit.carao.estellefelicity.campusequipmentloan.controller;

import edu.cit.carao.estellefelicity.campusequipmentloan.entity.LoanEntity;
import edu.cit.carao.estellefelicity.campusequipmentloan.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

        String startDateStr = (String) request.get("startDate");
        LocalDate startDate = null;
        if (startDateStr != null) {
            startDate = LocalDate.parse(startDateStr);  // YYYY-MM-DD
        }

        return loanService.createLoan(equipmentId, studentNo, startDate);
    }

    @PostMapping("/{id}/return")
    public LoanEntity returnLoan(@PathVariable Long id, @RequestBody Map<String, String> request) {
        String returnDateStr = request.get("returnDate");
        LocalDate returnDate = LocalDate.parse(returnDateStr);
        return loanService.returnLoan(id, returnDate);
    }
}