package org.example.cropmonitoringsystem.service;

import org.example.cropmonitoringsystem.dto.impl.CropDTO;

import java.util.List;

public interface CropService {
    void saveCrop(CropDTO cropDTO);
    List<CropDTO> getAllCrops();
}
