package org.example.cropmonitoringsystem.service.impl;

import org.example.cropmonitoringsystem.customObj.FieldResponse;
import org.example.cropmonitoringsystem.customObj.impl.FieldErrorResponse;
import org.example.cropmonitoringsystem.dao.FieldDao;
import org.example.cropmonitoringsystem.dto.impl.FieldDTO;
import org.example.cropmonitoringsystem.entity.CropEntity;
import org.example.cropmonitoringsystem.entity.FieldEntity;
import org.example.cropmonitoringsystem.exception.CropNotFound;
import org.example.cropmonitoringsystem.exception.FieldNotFound;
import org.example.cropmonitoringsystem.service.FieldService;
import org.example.cropmonitoringsystem.util.AppUtil;
import org.example.cropmonitoringsystem.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FieldServiceIMPL implements FieldService {
    @Autowired
    private FieldDao fieldDao;
    @Autowired
    private Mapping mapping;

    @Override
    public void saveField(FieldDTO fieldDTO) {
        FieldEntity fieldEntity = mapping.convertToFieldEntity(fieldDTO);

        if (fieldEntity.getFieldCode() == null || fieldEntity.getFieldCode().isEmpty()){
            fieldEntity.setFieldCode(AppUtil.createFieldId());
        }
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
        if (!tmpField.isPresent()){
            throw new FieldNotFound("Field not Found");
        } else {
            FieldEntity fieldEntity = tmpField.get();
            fieldEntity.setFieldName(updateFieldDTO.getFieldName());
            fieldEntity.setFieldLocation(updateFieldDTO.getFieldLocation());
            fieldEntity.setExtendSize(updateFieldDTO.getExtendSize());
            fieldEntity.setFieldImage1(updateFieldDTO.getFieldImage1());
            fieldEntity.setFieldImage1(updateFieldDTO.getFieldImage2());

            fieldDao.save(fieldEntity);
        }
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
}
