package org.example.cropmonitoringsystem.controller;

import lombok.RequiredArgsConstructor;
import org.example.cropmonitoringsystem.dto.impl.FieldDTO;
import org.example.cropmonitoringsystem.dto.impl.StaffDTO;
import org.example.cropmonitoringsystem.exception.FieldNotFound;
import org.example.cropmonitoringsystem.service.FieldService;
import org.example.cropmonitoringsystem.service.StaffService;
import org.example.cropmonitoringsystem.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/field")
@RequiredArgsConstructor
public class FieldController {
    @Autowired
    private final FieldService fieldService;
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> saveField(
        @RequestPart("fieldName") String fieldName,
        @RequestPart("latitude") String latitude,
        @RequestPart("longitude") String longitude,
        @RequestPart("extentSize") String extentSize,
        @RequestPart("fieldImage1") MultipartFile fieldImage1,
        @RequestPart("fieldImage2") MultipartFile fieldImage2
        ){
            try{
                Point fieldLocation = new Point(Double.parseDouble(latitude), Double.parseDouble(longitude));
                String base64FieldImage1 = AppUtil.toBase64FieldImage1(fieldImage1);
                String base64FieldImage2 = AppUtil.toBase64FieldImage2(fieldImage2);
                var fieldDTO = new FieldDTO();
                fieldDTO.setFieldCode(AppUtil.createFieldId());
                fieldDTO.setFieldName(fieldName);
                fieldDTO.setFieldLocation(fieldLocation);
                fieldDTO.setExtendSize(Double.parseDouble(extentSize));
                fieldDTO.setFieldImage1(base64FieldImage1);
                fieldDTO.setFieldImage2(base64FieldImage2);

                fieldService.saveField(fieldDTO);
                return new ResponseEntity<>(HttpStatus.CREATED);
            } catch (FieldNotFound e) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } catch (Exception e){
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }
}
