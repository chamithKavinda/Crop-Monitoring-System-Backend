package org.example.cropmonitoringsystem.dto.impl;

import jakarta.validation.constraints.*;
import lombok.*;
import org.example.cropmonitoringsystem.customObj.FieldResponse;
import org.example.cropmonitoringsystem.dto.SuperDTO;
import org.springframework.data.geo.Point;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FieldDTO implements SuperDTO, FieldResponse {

    private String fieldCode;

    @NotBlank(message = "Field name cannot be blank")
    @Pattern(regexp = "^[A-Za-z0-9 ]+$", message = "Field name must contain only alphanumeric characters and spaces")
    private String fieldName;

    @NotNull(message = "Field location cannot be null")
    private Point fieldLocation; // Assuming Point will be validated separately in logic

    @Positive(message = "Extent size must be a positive number")
    private double extendSize;

    @NotNull(message = "Crops list cannot be null")
    private List<CropDTO> crops;

    @NotNull(message = "Staff list cannot be null")
    private List<StaffDTO> staff;

    @NotBlank(message = "Field image 1 cannot be blank")
    @Pattern(regexp = "^[A-Za-z0-9._-]+\\.(jpg|jpeg|png|gif)$", message = "Field image 1 must be a valid image file (jpg, jpeg, png, gif)")
    private String fieldImage1;

    @NotBlank(message = "Field image 2 cannot be blank")
    @Pattern(regexp = "^[A-Za-z0-9._-]+\\.(jpg|jpeg|png|gif)$", message = "Field image 2 must be a valid image file (jpg, jpeg, png, gif)")
    private String fieldImage2;

    @NotNull(message = "Staff IDs list cannot be null")
    @Size(min = 1, message = "Staff IDs list must have at least one staff ID")
    @Pattern.List({
            @Pattern(regexp = "^[A-Za-z0-9_-]+$", message = "Each staff ID must contain only alphanumeric characters, underscores, or dashes")
    })
    private List<String> staffIds;
}
