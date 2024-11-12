package org.example.cropmonitoringsystem.controller;

import lombok.RequiredArgsConstructor;
import org.example.cropmonitoringsystem.dto.impl.StaffDTO;
import org.example.cropmonitoringsystem.exception.DataPersistFailedException;
import org.example.cropmonitoringsystem.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/staff")
@RequiredArgsConstructor
public class StaffController {
    @Autowired
    private final StaffService staffService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveStaff(@RequestBody StaffDTO staff){
        if (staff == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            try{
                staffService.saveStaff(staff);
                return new ResponseEntity<>(HttpStatus.CREATED);
            }catch (DataPersistFailedException e){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }catch (Exception e){
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
}
