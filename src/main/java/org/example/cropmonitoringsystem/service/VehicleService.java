package org.example.cropmonitoringsystem.service;

import org.example.cropmonitoringsystem.dto.impl.VehicleDTO;

import java.util.List;

public interface VehicleService {
    void saveVehicle(VehicleDTO vehicleDTO);
    List<VehicleDTO> getAllVehicles();
}
