package org.example.cropmonitoringsystem.service.impl;

import org.example.cropmonitoringsystem.customObj.FieldResponse;
import org.example.cropmonitoringsystem.customObj.impl.FieldErrorResponse;
import org.example.cropmonitoringsystem.dao.FieldDao;
import org.example.cropmonitoringsystem.dao.StaffDao;
import org.example.cropmonitoringsystem.dto.impl.FieldDTO;
import org.example.cropmonitoringsystem.entity.CropEntity;
import org.example.cropmonitoringsystem.entity.FieldEntity;
import org.example.cropmonitoringsystem.entity.StaffEntity;
import org.example.cropmonitoringsystem.exception.CropNotFound;
import org.example.cropmonitoringsystem.exception.FieldNotFound;
import org.example.cropmonitoringsystem.service.FieldService;
import org.example.cropmonitoringsystem.util.AppUtil;
import org.example.cropmonitoringsystem.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FieldServiceIMPL implements FieldService {
    @Autowired
    private FieldDao fieldDao;

    @Autowired
    private StaffDao staffDao;

    @Autowired
    private Mapping mapping;

    @Override
    @Transactional
    public void saveField(FieldDTO fieldDTO) {
        FieldEntity fieldEntity = mapping.convertToFieldEntity(fieldDTO);

        if (fieldEntity.getFieldCode() == null || fieldEntity.getFieldCode().isEmpty()){
            fieldEntity.setFieldCode(AppUtil.createFieldId());
        }

        List<StaffEntity> staff = getStaffFromIds(fieldDTO.getStaffIds());
        fieldEntity.setStaff(staff);

        fieldDao.save(fieldEntity);
    }

    @Override
    public List<FieldDTO> getAllFields() {
        List<FieldEntity> getAllFields = fieldDao.findAll();
        return mapping.convertFieldToDTOList(getAllFields);
    }

    @Override
    public FieldResponse getSelectedField(String fieldCode) {
        if (fieldDao.existsById(fieldCode)) {
            FieldEntity fieldEntityByFieldCode = fieldDao.getReferenceById(fieldCode);
            return mapping.convertToFieldDTO(fieldEntityByFieldCode);
        } else {
            return new FieldErrorResponse(0, "Field not Found");
        }
    }

    @Override
    public void updateField(FieldDTO updateFieldDTO) {
        Optional<FieldEntity> tmpField = fieldDao.findById(updateFieldDTO.getFieldCode());

        if (!tmpField.isPresent()) {
            throw new FieldNotFound("Field with code " + updateFieldDTO.getFieldCode() + " not found");
        }

        FieldEntity fieldEntity = tmpField.get();
        fieldEntity.setFieldName(updateFieldDTO.getFieldName());
        fieldEntity.setFieldLocation(updateFieldDTO.getFieldLocation());
        fieldEntity.setExtendSize(updateFieldDTO.getExtendSize());

        if (updateFieldDTO.getFieldImage1() != null) {
            fieldEntity.setFieldImage1(updateFieldDTO.getFieldImage1());
        }

        if (updateFieldDTO.getFieldImage2() != null) {
            fieldEntity.setFieldImage2(updateFieldDTO.getFieldImage2());
        }

        if (updateFieldDTO.getStaffIds() != null && !updateFieldDTO.getStaffIds().isEmpty()) {
            List<StaffEntity> staffEntities = staffDao.findAllById(updateFieldDTO.getStaffIds());
            fieldEntity.setStaff(staffEntities);
        }

        fieldDao.save(fieldEntity);
    }


    @Override
    public void deleteField(String fieldCode) {
        Optional<FieldEntity> findId = fieldDao.findById(fieldCode);
        if (!findId.isPresent()){
            throw new FieldNotFound("Field not Found");
        }else {
            fieldDao.deleteById(fieldCode);
        }
    }

    private List<StaffEntity> getStaffFromIds(List<String> staffIds){
        return staffDao.findAllById(staffIds);
    }
}
