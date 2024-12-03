package org.example.cropmonitoringsystem.controller;

import lombok.RequiredArgsConstructor;
import org.example.cropmonitoringsystem.customObj.VehicleResponse;
import org.example.cropmonitoringsystem.dto.impl.VehicleDTO;
import org.example.cropmonitoringsystem.exception.DataPersistFailedException;
import org.example.cropmonitoringsystem.exception.VehicleNotFound;
import org.example.cropmonitoringsystem.service.VehicleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("api/v1/vehicles")
@RequiredArgsConstructor
@Validated
@CrossOrigin("*")
public class VehicleController {
    @Autowired
    private final VehicleService vehicleService;
    static Logger logger = LoggerFactory.getLogger(VehicleController.class);

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createVehicle(@Valid @RequestBody VehicleDTO vehicle) {
        try {
            vehicleService.saveVehicle(vehicle);
            logger.info("Vehicle saved :" + vehicle);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistFailedException e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<VehicleDTO> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    @GetMapping(value = "/{vehicleCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public VehicleResponse getSelectedVehicle(@PathVariable("vehicleCode") String vehicleCode) {
        return vehicleService.getSelectedVehicle(vehicleCode);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping(value = "/{vehicleCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateVehicle(@PathVariable("vehicleCode") String vehicleCode, @Valid @RequestBody VehicleDTO vehicle) {
        try {
            vehicleService.updateVehicle(vehicleCode, vehicle);
            logger.info("Vehicle Updated :" + vehicle);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (VehicleNotFound e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{vehicleCode}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable("vehicleCode") String vehicleCode) {
        try {
            vehicleService.deleteVehicle(vehicleCode);
            logger.info("Vehicle deleted :" + vehicleCode);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (VehicleNotFound e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
