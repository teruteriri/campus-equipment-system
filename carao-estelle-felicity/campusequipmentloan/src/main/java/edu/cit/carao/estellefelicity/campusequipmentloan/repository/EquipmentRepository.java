package edu.cit.carao.estellefelicity.campusequipmentloan.repository;

import edu.cit.carao.estellefelicity.campusequipmentloan.entity.EquipmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipmentRepository extends JpaRepository<EquipmentEntity, Long> {
    List<EquipmentEntity> findByAvailabilityTrue();
}