package org.example.cropmonitoringsystem.service.impl;

import org.example.cropmonitoringsystem.dao.EquipmentDao;
import org.example.cropmonitoringsystem.dto.impl.EquipmentDTO;
import org.example.cropmonitoringsystem.entity.EquipmentEntity;
import org.example.cropmonitoringsystem.entity.VehicleEntity;
import org.example.cropmonitoringsystem.exception.DataPersistFailedException;
import org.example.cropmonitoringsystem.service.EquipmentService;
import org.example.cropmonitoringsystem.util.AppUtil;
import org.example.cropmonitoringsystem.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentServiceIMPL implements EquipmentService {
    @Autowired
    private EquipmentDao equipmentDao;
    @Autowired
    private Mapping mapping;

    @Override
    public void saveEquipment(EquipmentDTO equipmentDTO) {
        equipmentDTO.setEquipmentId(AppUtil.createEquipmentId());
        var equipmentEntity = mapping.convertToEntity(equipmentDTO);
        var savedEquipment = equipmentDao.save(equipmentEntity);
        if (savedEquipment == null){
            throw new DataPersistFailedException("Cannot save equipment");
        }
    }

    @Override
    public List<EquipmentDTO> getAllEquipments() {
        List<EquipmentEntity> getAllEquipments = equipmentDao.findAll();
        return mapping.convertEquipmentToDTOList(getAllEquipments);
    }
}
