package org.example.cropmonitoringsystem.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.cropmonitoringsystem.customObj.StaffResponse;
import org.example.cropmonitoringsystem.dto.impl.StaffDTO;
import org.example.cropmonitoringsystem.exception.DataPersistFailedException;
import org.example.cropmonitoringsystem.exception.StaffNotFound;
import org.example.cropmonitoringsystem.service.StaffService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/staff")
@RequiredArgsConstructor
public class StaffController {
    @Autowired
    private final StaffService staffService;
    static Logger logger = LoggerFactory.getLogger(StaffController.class);

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveStaff(@Valid @RequestBody StaffDTO staff){
        if (staff == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            try{
                staffService.saveStaff(staff);
                logger.info("Staff saved :" + staff);
                return new ResponseEntity<>(HttpStatus.CREATED);
            }catch (DataPersistFailedException e){
                logger.error(e.getMessage());
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }catch (Exception e){
                logger.error(e.getMessage());
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StaffDTO> getAllStaffs(){
        return staffService.getAllStaffs();
    }

    @GetMapping(value = "/{staffId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public StaffResponse getSelectedStaff(@PathVariable("staffId") String staffId){
        return staffService.getSelectedStaff(staffId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping(value = "/{staffId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateStaff(@PathVariable("staffId") String staffId, @RequestBody StaffDTO staff){
        try{
            if (staff == null && (staffId == null || staff.equals(""))){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            staffService.updateStaff(staffId,staff);
            logger.info("Staff Updated :" + staff);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (StaffNotFound e){
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{staffId}")
    public ResponseEntity<Void> deleteStaff(@PathVariable("staffId") String staffId){
        try{
            staffService.deleteStaff(staffId);
            logger.info("Staff deleted :" + staffId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (StaffNotFound e){
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
