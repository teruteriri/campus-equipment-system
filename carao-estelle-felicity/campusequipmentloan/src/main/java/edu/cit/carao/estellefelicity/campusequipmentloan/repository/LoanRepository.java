package edu.cit.carao.estellefelicity.campusequipmentloan.repository;

import edu.cit.carao.estellefelicity.campusequipmentloan.entity.LoanEntity;
import edu.cit.carao.estellefelicity.campusequipmentloan.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends JpaRepository<LoanEntity, Long> {
    int countByStudentAndStatus(StudentEntity student, LoanEntity.LoanStatus status);
}