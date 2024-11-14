package org.example.cropmonitoringsystem.service.impl;


import org.example.cropmonitoringsystem.customObj.CropDetailsResponse;
import org.example.cropmonitoringsystem.customObj.impl.CropDetailsErrorResponse;
import org.example.cropmonitoringsystem.customObj.impl.CropErrorResponse;
import org.example.cropmonitoringsystem.dao.CropDao;
import org.example.cropmonitoringsystem.dao.CropDetailsDao;
import org.example.cropmonitoringsystem.dao.FieldDao;
import org.example.cropmonitoringsystem.dao.StaffDao;
import org.example.cropmonitoringsystem.dto.impl.CropDetailsDTO;
import org.example.cropmonitoringsystem.entity.CropDetailsEntity;
import org.example.cropmonitoringsystem.entity.CropEntity;
import org.example.cropmonitoringsystem.entity.FieldEntity;
import org.example.cropmonitoringsystem.entity.StaffEntity;
import org.example.cropmonitoringsystem.service.CropDetailsService;
import org.example.cropmonitoringsystem.util.AppUtil;
import org.example.cropmonitoringsystem.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CropDetailsServiceIMPL implements CropDetailsService {
    @Autowired
    private CropDetailsDao cropDetailsDao;

    @Autowired
    private CropDao cropDao;

    @Autowired
    private StaffDao staffDao;

    @Autowired
    private FieldDao fieldDao;

    @Autowired
    private Mapping mapping;

    @Override
    @Transactional
    public void saveCropDetails(CropDetailsDTO cropDetailsDTO) {
        CropDetailsEntity cropDetailsEntity = mapping.convertToCropDetailsEntity(cropDetailsDTO);

        if (cropDetailsEntity.getLogCode() == null || cropDetailsEntity.getLogCode().isEmpty()){
            cropDetailsEntity.setLogCode(AppUtil.createLogCode());
        }

        List<FieldEntity> fields = getFieldsFromCodes(cropDetailsDTO.getFieldCodes());
        List<CropEntity> crops = getCropsFromCodes(cropDetailsDTO.getCropCodes());
        List<StaffEntity> staff = getStaffFromIds(cropDetailsDTO.getStaffIds());

        cropDetailsEntity.setField(fields);
        cropDetailsEntity.setCrop(crops);
        cropDetailsEntity.setStaff(staff);

        cropDetailsDao.save(cropDetailsEntity);
    }

    @Override
    public List<CropDetailsDTO> getAllCropDetails() {
        List<CropDetailsEntity> getAllCropDetails = cropDetailsDao.findAll();
        return mapping.convertCropDetailsToDTOList(getAllCropDetails);
    }

    @Override
    public CropDetailsResponse getSelectedCropDetail(String logCode) {
        if (cropDetailsDao.existsById(logCode)) {
            CropDetailsEntity cropDetailsEntityByLogCode = cropDetailsDao.getReferenceById(logCode);
            return mapping.convertToCropDetailsDTO(cropDetailsEntityByLogCode);
        } else {
            return new CropDetailsErrorResponse(0, "Crop Details not Found");
        }
    }


    private List<FieldEntity> getFieldsFromCodes(List<String> fieldCodes){
        return fieldDao.findAllById(fieldCodes);
    }

    private List<CropEntity> getCropsFromCodes(List<String> cropCodes){
        return cropDao.findAllById(cropCodes);
    }

    private List<StaffEntity> getStaffFromIds(List<String> staffIds){
        return staffDao.findAllById(staffIds);
    }
}
