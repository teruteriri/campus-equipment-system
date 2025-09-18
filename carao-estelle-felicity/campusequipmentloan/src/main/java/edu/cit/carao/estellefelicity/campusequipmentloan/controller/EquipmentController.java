package edu.cit.carao.estellefelicity.campusequipmentloan.controller;

import edu.cit.carao.estellefelicity.campusequipmentloan.entity.EquipmentEntity;
import edu.cit.carao.estellefelicity.campusequipmentloan.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipment")
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    @GetMapping("/available")
    public List<EquipmentEntity> getAvailableEquipment() {
        return equipmentService.getAvailableEquipment();
    }

    @GetMapping("/all")
    public List<EquipmentEntity> getAllEquipment() {
        return equipmentService.getAllEquipment();
    }

    @PostMapping("/add")
    public EquipmentEntity addEquipment(@RequestBody EquipmentEntity equipment) {
        return equipmentService.addEquipment(equipment);
    }

    @DeleteMapping("/{id}")
    public void deleteEquipment(@PathVariable Long id) {
        equipmentService.deleteEquipment(id);
    }
}

