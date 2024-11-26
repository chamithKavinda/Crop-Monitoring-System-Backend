package org.example.cropmonitoringsystem.dto.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.cropmonitoringsystem.customObj.VehicleResponse;
import org.example.cropmonitoringsystem.dto.SuperDTO;

import jakarta.validation.constraints.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VehicleDTO implements SuperDTO, VehicleResponse {
    private String vehicleCode;

    @NotBlank(message = "License plate number is required.")
    @Size(max = 15, message = "License plate number must not exceed 15 characters.")
    private String licensePlateNumber;

    @NotBlank(message = "Vehicle category is required.")
    private String vehicleCategory;

    @NotBlank(message = "Fuel type is required.")
    private String fuelType;

    @NotBlank(message = "Status is required.")
    private String status;

    @NotBlank(message = "Staff ID is required.")
    private String staffId;

    @Size(max = 255, message = "Remarks must not exceed 255 characters.")
    private String remarks;
}
