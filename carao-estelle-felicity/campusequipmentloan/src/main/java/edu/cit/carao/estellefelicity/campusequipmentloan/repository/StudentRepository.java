package edu.cit.carao.estellefelicity.campusequipmentloan.repository;

import edu.cit.carao.estellefelicity.campusequipmentloan.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
    Optional<StudentEntity> findByStudentNo(String studentNo);
}