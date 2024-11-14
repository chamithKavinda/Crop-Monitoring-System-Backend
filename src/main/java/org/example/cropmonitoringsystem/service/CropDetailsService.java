package org.example.cropmonitoringsystem.service;

import org.example.cropmonitoringsystem.customObj.CropDetailsResponse;
import org.example.cropmonitoringsystem.dto.impl.CropDetailsDTO;

import java.util.List;

public interface CropDetailsService {
    void saveCropDetails(CropDetailsDTO cropDetailsDTO);
    List<CropDetailsDTO> getAllCropDetails();
    CropDetailsResponse getSelectedCropDetail(String logCode);
    void updateCropDetails(CropDetailsDTO updatecropDetailsDTO);
    void deleteCropDetails(String logCode);
}
