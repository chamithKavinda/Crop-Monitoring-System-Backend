package org.example.cropmonitoringsystem.service.impl;

import org.example.cropmonitoringsystem.customObj.EquipmentResponse;
import org.example.cropmonitoringsystem.customObj.VehicleResponse;
import org.example.cropmonitoringsystem.customObj.impl.EquipmentErrorResponse;
import org.example.cropmonitoringsystem.customObj.impl.VehicleErrorResponse;
import org.example.cropmonitoringsystem.dao.EquipmentDao;
import org.example.cropmonitoringsystem.dto.impl.EquipmentDTO;
import org.example.cropmonitoringsystem.entity.EquipmentEntity;
import org.example.cropmonitoringsystem.entity.VehicleEntity;
import org.example.cropmonitoringsystem.enums.Status;
import org.example.cropmonitoringsystem.enums.Type;
import org.example.cropmonitoringsystem.exception.DataPersistFailedException;
import org.example.cropmonitoringsystem.exception.EquipmentNotFound;
import org.example.cropmonitoringsystem.exception.VehicleNotFound;
import org.example.cropmonitoringsystem.service.EquipmentService;
import org.example.cropmonitoringsystem.util.AppUtil;
import org.example.cropmonitoringsystem.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipmentServiceIMPL implements EquipmentService {
    @Autowired
    private EquipmentDao equipmentDao;
    @Autowired
    private Mapping mapping;

    @Override
    public void saveEquipment(EquipmentDTO equipmentDTO) {
        equipmentDTO.setEquipmentId(AppUtil.createEquipmentId());
        var equipmentEntity = mapping.convertToEquipmentEntity(equipmentDTO);
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

    @Override
    public EquipmentResponse getSelectedEquipment(String equipmentId) {
        if (equipmentDao.existsById(equipmentId)) {
            EquipmentEntity equipmentEntityByEquipmentId = equipmentDao.getReferenceById(equipmentId);
            return mapping.convertToEquipmentDTO(equipmentEntityByEquipmentId);
        } else {
            return new EquipmentErrorResponse(0, "Equipment not Found");
        }
    }

    @Override
    public void updateEquipment(String equipmentId, EquipmentDTO incomeequipmentDTO) {
        Optional<EquipmentEntity> tmpEquipmentEntity = equipmentDao.findById(equipmentId);

        if (!tmpEquipmentEntity.isPresent()) {
            throw new EquipmentNotFound("Equipment not found");
        } else {
            EquipmentEntity equipmentEntity = tmpEquipmentEntity.get();

            equipmentEntity.setName(incomeequipmentDTO.getName());
            equipmentEntity.setType(Type.valueOf(incomeequipmentDTO.getType()));
            equipmentEntity.setStatus(Status.valueOf(incomeequipmentDTO.getStatus()));

            equipmentDao.save(equipmentEntity);
        }
    }

    @Override
    public void deleteEquipment(String equipmentId) {
        Optional<EquipmentEntity> findId = equipmentDao.findById(equipmentId);
        if (!findId.isPresent()){
            throw new EquipmentNotFound("Equipment not Found");
        }else {
            equipmentDao.deleteById(equipmentId);
        }
    }

}