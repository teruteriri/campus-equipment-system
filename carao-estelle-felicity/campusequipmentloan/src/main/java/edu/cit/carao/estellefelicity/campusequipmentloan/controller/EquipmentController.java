package edu.cit.carao.estellefelicity.campusequipmentloan.controller;

import edu.cit.carao.estellefelicity.campusequipmentloan.entity.EquipmentEntity;
import edu.cit.carao.estellefelicity.campusequipmentloan.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
