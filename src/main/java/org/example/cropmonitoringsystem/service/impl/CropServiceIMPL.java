package org.example.cropmonitoringsystem.service.impl;

import org.example.cropmonitoringsystem.dao.CropDao;
import org.example.cropmonitoringsystem.dto.impl.CropDTO;
import org.example.cropmonitoringsystem.entity.CropEntity;
import org.example.cropmonitoringsystem.entity.EquipmentEntity;
import org.example.cropmonitoringsystem.service.CropService;
import org.example.cropmonitoringsystem.util.AppUtil;
import org.example.cropmonitoringsystem.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CropServiceIMPL implements CropService {
    @Autowired
    private CropDao cropDao;
    @Autowired
    private Mapping mapping;

    @Override
    public void saveCrop(CropDTO cropDTO) {
        CropEntity cropEntity = mapping.convertToCropEntity(cropDTO);

        if (cropEntity.getCropCode() == null || cropEntity.getCropCode().isEmpty()){
            cropEntity.setCropCode(AppUtil.createCropId());
        }

        cropDao.save(cropEntity);
    }

    @Override
    public List<CropDTO> getAllCrops() {
        List<CropEntity> getAllCrops = cropDao.findAll();
        return mapping.convertCropToDTOList(getAllCrops);
    }
}
