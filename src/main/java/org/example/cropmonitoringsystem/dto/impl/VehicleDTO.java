package org.example.cropmonitoringsystem.dto.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.cropmonitoringsystem.customObj.VehicleResponse;
import org.example.cropmonitoringsystem.dto.SuperDTO;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VehicleDTO implements SuperDTO , VehicleResponse {
    private String vehicleCode;
    private String licensePlateNumber;
    private String vehicleCategory;
    private String fuelType;
    private String status;
    private String staffId;
    private String remarks;
}
