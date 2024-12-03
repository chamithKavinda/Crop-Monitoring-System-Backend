package org.example.cropmonitoringsystem.dto.impl;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.cropmonitoringsystem.customObj.EquipmentResponse;
import org.example.cropmonitoringsystem.dto.SuperDTO;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EquipmentDTO implements SuperDTO, EquipmentResponse {
    @Id
    private String equipmentId;

    @NotBlank(message = "Equipment name cannot be blank")
    @Pattern(regexp = "^[A-Za-z0-9 ]+$", message = "Equipment name must contain only alphanumeric characters and spaces")
    @Size(max = 50, message = "Equipment name must not exceed 50 characters")
    private String name;

    @NotBlank(message = "Equipment type cannot be blank")
    @Pattern(regexp = "^(ELECTRICAL|MECHANICAL)$", message = "Type must be one of the following: ELECTRICAL,MECHANICAL")
    private String type;

    @NotBlank(message = "Status cannot be blank")
    @Pattern(regexp = "^(ACTIVE|INACTIVE)$", message = "Status must be one of the following: ACTIVE, INACTIVE")
    private String status;

    @NotBlank(message = "Staff ID cannot be blank")
    @Pattern(regexp = "^[A-Za-z0-9_-]+$", message = "Staff ID must contain only alphanumeric characters, underscores, or dashes")
    private String staffId;

    @NotBlank(message = "Field code cannot be blank")
    @Pattern(regexp = "^[A-Za-z0-9_-]+$", message = "Field code must contain only alphanumeric characters, underscores, or dashes")
    private String fieldCode;
}
