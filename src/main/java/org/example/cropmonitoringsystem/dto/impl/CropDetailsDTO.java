package org.example.cropmonitoringsystem.dto.impl;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.cropmonitoringsystem.customObj.CropDetailsResponse;
import org.example.cropmonitoringsystem.dto.SuperDTO;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CropDetailsDTO implements SuperDTO, CropDetailsResponse {

    private String logCode;

    @NotNull(message = "Log date cannot be null")
    private LocalDate logDate; 

    @NotBlank(message = "Log details cannot be blank")
    @Size(max = 500, message = "Log details must not exceed 500 characters")
    private String logDetails;

    @NotBlank(message = "Observed image cannot be blank")
    @Pattern(regexp = "^[A-Za-z0-9._-]+\\.(jpg|jpeg|png|gif)$", message = "Observed image must be a valid image file (jpg, jpeg, png, gif)")
    private String observedImage;

    @NotNull(message = "Field codes list cannot be null")
    @Size(min = 1, message = "Field codes list must have at least one field code")
    @Pattern.List({
            @Pattern(regexp = "^[A-Za-z0-9_-]+$", message = "Each field code must contain only alphanumeric characters, underscores, or dashes")
    })
    private List<String> fieldCodes;

    @NotNull(message = "Crop codes list cannot be null")
    @Size(min = 1, message = "Crop codes list must have at least one crop code")
    @Pattern.List({
            @Pattern(regexp = "^[A-Za-z0-9_-]+$", message = "Each crop code must contain only alphanumeric characters, underscores, or dashes")
    })
    private List<String> cropCodes;

    @NotNull(message = "Staff IDs list cannot be null")
    @Size(min = 1, message = "Staff IDs list must have at least one staff ID")
    @Pattern.List({
            @Pattern(regexp = "^[A-Za-z0-9_-]+$", message = "Each staff ID must contain only alphanumeric characters, underscores, or dashes")
    })
    private List<String> staffIds;
}
