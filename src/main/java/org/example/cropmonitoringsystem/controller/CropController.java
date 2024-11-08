package org.example.cropmonitoringsystem.controller;

import lombok.RequiredArgsConstructor;
import org.example.cropmonitoringsystem.dto.impl.CropDTO;
import org.example.cropmonitoringsystem.exception.CropNotFound;
import org.example.cropmonitoringsystem.service.CropService;
import org.example.cropmonitoringsystem.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/v1/crop")
@RequiredArgsConstructor
public class CropController {
    @Autowired
    private final CropService cropService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> saveCrop(
            @RequestPart("cropCommonName") String cropCommonName,
            @RequestPart("cropScientificName") String cropScientificName,
            @RequestPart("cropImage")MultipartFile cropImage,
            @RequestPart("category") String category,
            @RequestPart("cropSeason") String cropSeason,
            @RequestPart("filedCode") String fieldCode
            ){
                try {
                    String base64CropImage = AppUtil.toBase64CropImage(cropImage);
                    var cropDTO = new CropDTO();
                    cropDTO.setCropCode(AppUtil.createCropId());
                    cropDTO.setCropCommonName(cropCommonName);
                    cropDTO.setCropScientificName(cropScientificName);
                    cropDTO.setCropImage(base64CropImage);
                    cropDTO.setCategory(category);
                    cropDTO.setCropSeason(cropSeason);
                    cropDTO.setFieldCode(fieldCode);

                    cropService.saveCrop(cropDTO);
                    return new ResponseEntity<>(HttpStatus.CREATED);
                } catch (CropNotFound e) {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                } catch (Exception e) {
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CropDTO> getAllCrops(){
        return cropService.getAllCrops();
    }



}
