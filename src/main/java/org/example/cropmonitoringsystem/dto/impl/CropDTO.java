package org.example.cropmonitoringsystem.dto.impl;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.cropmonitoringsystem.customObj.CropResponse;
import org.example.cropmonitoringsystem.dto.SuperDTO;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CropDTO implements SuperDTO, CropResponse {

    private String cropCode;

    @NotBlank(message = "Crop common name cannot be blank")
    @Pattern(regexp = "^[A-Za-z0-9 ]+$", message = "Crop common name must contain only alphanumeric characters and spaces")
    @Size(max = 100, message = "Crop common name must not exceed 100 characters")
    private String cropCommonName;

    @NotBlank(message = "Crop scientific name cannot be blank")
    @Pattern(regexp = "^[A-Za-z0-9 .]+$", message = "Crop scientific name must contain only alphanumeric characters, spaces, and periods")
    @Size(max = 150, message = "Crop scientific name must not exceed 150 characters")
    private String cropScientificName;

    @NotBlank(message = "Crop image cannot be blank")
    @Pattern(regexp = "^[A-Za-z0-9._-]+\\.(jpg|jpeg|png|gif)$", message = "Crop image must be a valid image file (jpg, jpeg, png, gif)")
    private String cropImage;

    @NotBlank(message = "Category cannot be blank")
    @Pattern(regexp = "^[A-Za-z0-9 ]+$", message = "Category must contain only alphanumeric characters and spaces")
    @Size(max = 50, message = "Category must not exceed 50 characters")
    private String category;

    @NotBlank(message = "Crop season cannot be blank")
    @Pattern(regexp = "^[A-Za-z0-9 ]+$", message = "Crop season must contain only alphanumeric characters and spaces")
    @Size(max = 50, message = "Crop season must not exceed 50 characters")
    private String cropSeason;

    @NotBlank(message = "Field code cannot be blank")
    private String fieldCode;
}
