package org.example.cropmonitoringsystem.service.impl;

import org.example.cropmonitoringsystem.customObj.VehicleResponse;
import org.example.cropmonitoringsystem.customObj.impl.VehicleErrorResponse;
import org.example.cropmonitoringsystem.dao.VehicleDao;
import org.example.cropmonitoringsystem.dto.impl.VehicleDTO;
import org.example.cropmonitoringsystem.entity.VehicleEntity;
import org.example.cropmonitoringsystem.exception.DataPersistFailedException;
import org.example.cropmonitoringsystem.exception.VehicleNotFound;
import org.example.cropmonitoringsystem.service.VehicleService;
import org.example.cropmonitoringsystem.util.AppUtil;
import org.example.cropmonitoringsystem.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceIMPL implements VehicleService {
    @Autowired
    private VehicleDao vehicleDao;
    @Autowired
    private Mapping mapping;

    @Override
    public void saveVehicle(VehicleDTO vehicleDTO) {
        System.out.println("1");
        vehicleDTO.setVehicleCode(AppUtil.createVehicleCode());
        System.out.println("2");
        var vehicleEntity = mapping.convertToEntity(vehicleDTO);
        System.out.println("3");
        var savedVehicle = vehicleDao.save(vehicleEntity);
        System.out.println("4");
        if (savedVehicle == null){
            System.out.println("5");
            throw new DataPersistFailedException("Cannot save vehicle");
        }
    }

    @Override
    public List<VehicleDTO> getAllVehicles() {
        List<VehicleEntity> getAllVehicles = vehicleDao.findAll();
        return mapping.convertVehicleToDTOList(getAllVehicles);
    }

    @Override
    public VehicleResponse getSelectedVehicle(String vehicleCode) {
        if (vehicleDao.existsById(vehicleCode)){
            VehicleEntity vehicleEntityByVehicleCode = vehicleDao.getReferenceById(vehicleCode);
            return (VehicleResponse) mapping.convertToVehicleDTO(vehicleEntityByVehicleCode);
        }else {
            return new VehicleErrorResponse(0,"Vehicle not Found");
        }
    }

    @Override
    public void updateVehicle(String vehicleCode, VehicleDTO incomevehicleDTO) {
        Optional<VehicleEntity> tmpVehicleEntity = vehicleDao.findById(vehicleCode);

        if (!tmpVehicleEntity.isPresent()) {
            throw new VehicleNotFound("Vehicle not found");
        } else {
            VehicleEntity vehicleEntity = tmpVehicleEntity.get();

            vehicleEntity.setVehicleCategory(incomevehicleDTO.getVehicleCategory());
            vehicleEntity.setFuelType(incomevehicleDTO.getFuelType());
            vehicleEntity.setLicensePlateNumber(incomevehicleDTO.getLicensePlateNumber());
            vehicleEntity.setStatus(incomevehicleDTO.getStatus());
            vehicleEntity.setRemarks(incomevehicleDTO.getRemarks());

            vehicleDao.save(vehicleEntity);
        }
    }

    @Override
    public void deleteVehicle(String vehicleCode) {
        Optional<VehicleEntity> findId = vehicleDao.findById(vehicleCode);
        if (!findId.isPresent()){
            throw new VehicleNotFound("Vehicle not Found");
        }else {
            vehicleDao.deleteById(vehicleCode);
        }
    }


}
