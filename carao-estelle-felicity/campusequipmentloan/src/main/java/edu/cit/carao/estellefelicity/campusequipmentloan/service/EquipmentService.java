package edu.cit.carao.estellefelicity.campusequipmentloan.service;

import edu.cit.carao.estellefelicity.campusequipmentloan.entity.EquipmentEntity;
import edu.cit.carao.estellefelicity.campusequipmentloan.repository.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentService {
    @Autowired
    private EquipmentRepository equipmentRepository;

    public List<EquipmentEntity> getAvailableEquipment() {
        return equipmentRepository.findByAvailabilityTrue();
    }

    public List<EquipmentEntity> getAllEquipment() {
        return equipmentRepository.findAll();
    }

    public EquipmentEntity findById(Long id) {
        return equipmentRepository.findById(id).orElse(null);
    }

    public EquipmentEntity save(EquipmentEntity equipment) {
        return equipmentRepository.save(equipment);
    }

    public EquipmentEntity addEquipment(EquipmentEntity equipment) {
        if (equipment.getAvailability() == null) {
            equipment.setAvailability(true);
        }
        return equipmentRepository.save(equipment);
    }

    public void deleteEquipment(Long id) {
        equipmentRepository.deleteById(id);
    }
}