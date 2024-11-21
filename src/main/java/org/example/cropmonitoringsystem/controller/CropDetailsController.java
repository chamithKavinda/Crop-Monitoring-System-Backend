package org.example.cropmonitoringsystem.controller;

import lombok.RequiredArgsConstructor;
import org.example.cropmonitoringsystem.customObj.CropDetailsResponse;
import org.example.cropmonitoringsystem.dto.impl.CropDetailsDTO;
import org.example.cropmonitoringsystem.exception.CropDetailsNotFound;
import org.example.cropmonitoringsystem.exception.CropNotFound;
import org.example.cropmonitoringsystem.service.CropDetailsService;
import org.example.cropmonitoringsystem.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/cropDetails")
@RequiredArgsConstructor
public class CropDetailsController {
    @Autowired
    private final CropDetailsService cropDetailsService;
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> saveCropDetails(
        @RequestPart("logDetails") String logDetails,
        @RequestPart("logDate") String logDate,
        @RequestPart("observedImage") MultipartFile observedImage,
        @RequestPart("fieldCodes") String fieldCodes,
        @RequestPart("cropCodes") String cropCodes,
        @RequestPart("staffIds") String staffIds
    ){
        try {
            List<String> fieldCodeList = Arrays.asList(fieldCodes.split(","));
            List<String> cropCodeList = Arrays.asList(cropCodes.split(","));
            List<String> staffIdList = Arrays.asList(staffIds.split(","));

            var cropDetailsDTO = new CropDetailsDTO();

            cropDetailsDTO.setLogCode(AppUtil.createLogCode());
            cropDetailsDTO.setLogDetails(logDetails);
            cropDetailsDTO.setLogDate(LocalDate.parse(logDate));
            cropDetailsDTO.setObservedImage(AppUtil.toBase64ObservedImage(observedImage));
            cropDetailsDTO.setFieldCodes(fieldCodeList);
            cropDetailsDTO.setCropCodes(cropCodeList);
            cropDetailsDTO.setStaffIds(staffIdList);

            cropDetailsService.saveCropDetails(cropDetailsDTO);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (CropDetailsNotFound e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CropDetailsDTO> getAllCropDetails(){
        return cropDetailsService.getAllCropDetails();
    }

    @GetMapping(value = "/{logCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CropDetailsResponse getSelectedCropDetail(@PathVariable("logCode") String logCode){
        return cropDetailsService.getSelectedCropDetail(logCode);
    }

    @PatchMapping(value = "/{logCode}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateCropDetails(
            @PathVariable("logCode") String logCode,
            @RequestPart("logDate") String logDate,
            @RequestPart("logDetails") String logDetails,
            @RequestPart("observedImage") MultipartFile observedImage,
            @RequestPart("fieldCodes") String fieldCodes,
            @RequestPart("cropCodes") String cropCodes,
            @RequestPart("staffIds") String staffIds) {
        try {
            List<String> fieldCodeList = Arrays.asList(fieldCodes.split(","));
            List<String> cropCodeList = Arrays.asList(cropCodes.split(","));
            List<String> staffIdList = Arrays.asList(staffIds.split(","));

            var updatecropDetailsDTO = new CropDetailsDTO();
            updatecropDetailsDTO.setLogCode(logCode);
            updatecropDetailsDTO.setLogDate(LocalDate.parse(logDate));
            updatecropDetailsDTO.setLogDetails(logDetails);
            updatecropDetailsDTO.setObservedImage(AppUtil.toBase64ObservedImage(observedImage));
            updatecropDetailsDTO.setFieldCodes(fieldCodeList);
            updatecropDetailsDTO.setCropCodes(cropCodeList);
            updatecropDetailsDTO.setStaffIds(staffIdList);

            cropDetailsService.updateCropDetails(updatecropDetailsDTO);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (CropDetailsNotFound e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{logCode}")
    public ResponseEntity<Void> deleteCropDetails(@PathVariable("logCode") String logCode){
        try{
            cropDetailsService.deleteCropDetails(logCode);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (CropDetailsNotFound e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
