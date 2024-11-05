package org.example.cropmonitoringsystem.util;

import org.example.cropmonitoringsystem.dto.impl.VehicleDTO;
import org.example.cropmonitoringsystem.entity.VehicleEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class Mapping {
    @Autowired
    private ModelMapper modelMapper;

    //VehicleEntity and DTO
    public VehicleDTO convertToDTO(VehicleDTO vehicle){
        return modelMapper.map(vehicle, VehicleDTO.class);
    }
    public VehicleEntity convertToEntity(VehicleDTO dto){
        return modelMapper.map(dto, VehicleEntity.class);
    }
    public List<VehicleDTO> convertVehicleToDTOList(List<VehicleEntity> vehicles){
        return modelMapper.map(vehicles, new TypeToken<List<VehicleDTO>>(){}.getType());
    }
}
