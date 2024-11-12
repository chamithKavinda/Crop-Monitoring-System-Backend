package org.example.cropmonitoringsystem.service;

import org.example.cropmonitoringsystem.customObj.FieldResponse;
import org.example.cropmonitoringsystem.dto.impl.FieldDTO;

import java.util.List;

public interface FieldService {
    void saveField(FieldDTO fieldDTO);
    List<FieldDTO> getAllFields();
    FieldResponse getSelectedField(String fieldCode);
}
