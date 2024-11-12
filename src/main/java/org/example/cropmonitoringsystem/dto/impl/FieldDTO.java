package org.example.cropmonitoringsystem.dto.impl;

import lombok.*;
import org.example.cropmonitoringsystem.customObj.FieldResponse;
import org.example.cropmonitoringsystem.dto.SuperDTO;
import org.springframework.data.geo.Point;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FieldDTO implements SuperDTO, FieldResponse {
    private String fieldCode;
    private String fieldName;
    private Point fieldLocation;
    private double extendSize;
    private List<CropDTO> crops;
    private List<StaffDTO> staff;
    private String fieldImage1;
    private String fieldImage2;

}
