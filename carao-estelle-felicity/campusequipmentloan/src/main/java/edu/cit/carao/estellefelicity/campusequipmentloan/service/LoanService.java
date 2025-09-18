package edu.cit.carao.estellefelicity.campusequipmentloan.service;

import edu.cit.carao.estellefelicity.campusequipmentloan.entity.EquipmentEntity;
import edu.cit.carao.estellefelicity.campusequipmentloan.entity.LoanEntity;
import edu.cit.carao.estellefelicity.campusequipmentloan.entity.StudentEntity;
import edu.cit.carao.estellefelicity.campusequipmentloan.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private EquipmentService equipmentService;

    @Autowired
    private StudentService studentService;

    public LoanEntity createLoan (Long equipmentId, String studentNo, LocalDate startDate) {
        if (startDate == null) {
            startDate = LocalDate.now();
        }

        StudentEntity student = studentService.findByStudentNo(studentNo);
        if (student == null) {
            throw new RuntimeException("Student not found");
        }

        int activeLoans = loanRepository.countByStudentAndStatus(student, LoanEntity.LoanStatus.ACTIVE);
        if (activeLoans >= 2) {
            throw new RuntimeException("Student already has maximum active loans (2)");
        }

        EquipmentEntity equipment = equipmentService.findById(equipmentId);
        if (equipment == null) {
            throw new RuntimeException("Equipment not found");
        }

        if (!equipment.getAvailability()) {
            throw new RuntimeException("Equipment not available");
        }

        LoanEntity loan = new LoanEntity(equipment, student, startDate);

        equipment.setAvailability(false);
        equipmentService.save(equipment);

        return loanRepository.save(loan);
    }

    public LoanEntity returnLoan (Long loanId, LocalDate returnDate) {
        LoanEntity loan = loanRepository.findById(loanId).orElse(null);
        if (loan == null) {
            throw new RuntimeException("Loan not found");
        }

        if (loan.getStatus() != LoanEntity.LoanStatus.ACTIVE) {
            throw new RuntimeException("Loan is not active");
        }

        loan.setReturnDate(returnDate);

        if (returnDate.isAfter(loan.getDueDate())) {
            loan.setStatus(LoanEntity.LoanStatus.OVERDUE);
        } else {
            loan.setStatus(LoanEntity.LoanStatus.RETURNED);
        }

        EquipmentEntity equipment = loan.getEquipment();
        equipment.setAvailability(true);
        equipmentService.save(equipment);

        return loanRepository.save(loan);
    }

    public BigDecimal calculatePenalty(LoanEntity loan) {
        if (loan.getReturnDate() == null || !loan.getReturnDate().isAfter(loan.getDueDate())) {
            return BigDecimal.ZERO;
        }

        long daysLate = ChronoUnit.DAYS.between(loan.getDueDate(), loan.getReturnDate());
        return BigDecimal.valueOf(50).multiply(BigDecimal.valueOf(daysLate));
    }
}