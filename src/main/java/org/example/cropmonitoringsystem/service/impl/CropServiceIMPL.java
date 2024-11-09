package org.example.cropmonitoringsystem.service.impl;

import org.example.cropmonitoringsystem.customObj.CropResponse;
import org.example.cropmonitoringsystem.customObj.impl.CropErrorResponse;
import org.example.cropmonitoringsystem.dao.CropDao;
import org.example.cropmonitoringsystem.dto.impl.CropDTO;
import org.example.cropmonitoringsystem.entity.CropEntity;
import org.example.cropmonitoringsystem.entity.EquipmentEntity;
import org.example.cropmonitoringsystem.exception.CropNotFound;
import org.example.cropmonitoringsystem.exception.EquipmentNotFound;
import org.example.cropmonitoringsystem.service.CropService;
import org.example.cropmonitoringsystem.util.AppUtil;
import org.example.cropmonitoringsystem.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public CropResponse getSelectedCrop(String cropCode) {
        if (cropDao.existsById(cropCode)) {
            CropEntity cropEntityByCropCode = cropDao.getReferenceById(cropCode);
            return mapping.convertToCropDTO(cropEntityByCropCode);
        } else {
            return new CropErrorResponse(0, "Crop not Found");
        }
    }

    @Override
    public void updateCrop(CropDTO updateCropDTO) {
        Optional<CropEntity> tmpCrop = cropDao.findById(updateCropDTO.getCropCode());
        if (!tmpCrop.isPresent()) {
            throw new CropNotFound("Crop not Found");
        } else {
            CropEntity cropEntity = tmpCrop.get();
            cropEntity.setCategory(updateCropDTO.getCategory());
            cropEntity.setCropCommonName(updateCropDTO.getCropCommonName());
            cropEntity.setCropScientificName(updateCropDTO.getCropScientificName());
            cropEntity.setCropSeason(updateCropDTO.getCropSeason());
            cropEntity.setCropImage(updateCropDTO.getCropImage());

            // Save the updated entity
            cropDao.save(cropEntity);  // This line ensures the entity is saved to the database
        }
    }

    @Override
    public void deleteCrop(String cropCode) {
        Optional<CropEntity> findId = cropDao.findById(cropCode);
        if (!findId.isPresent()){
            throw new CropNotFound("Crop not Found");
        }else {
            cropDao.deleteById(cropCode);
        }
    }
}
