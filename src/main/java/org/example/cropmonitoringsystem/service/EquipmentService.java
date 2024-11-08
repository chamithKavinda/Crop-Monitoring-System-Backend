package org.example.cropmonitoringsystem.service;

import org.example.cropmonitoringsystem.customObj.EquipmentResponse;
import org.example.cropmonitoringsystem.dto.impl.EquipmentDTO;

import java.util.List;

public interface EquipmentService {
    void saveEquipment(EquipmentDTO equipmentDTO);
    List<EquipmentDTO> getAllEquipments();
    EquipmentResponse getSelectedEquipment(String equipmentId);
    void updateEquipment(String equipmentId, EquipmentDTO equipmentDTO);
    void deleteEquipment(String equipmentId);
}