package org.example.cropmonitoringsystem.dto.impl;

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
    private String cropCommonName;
    private String cropScientificName;
    private String cropImage;
    private String category;
    private String cropSeason;
    private String fieldCode;
}
