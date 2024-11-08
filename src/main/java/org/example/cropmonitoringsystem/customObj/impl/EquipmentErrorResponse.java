package org.example.cropmonitoringsystem.customObj.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.cropmonitoringsystem.customObj.EquipmentResponse;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EquipmentErrorResponse implements EquipmentResponse, Serializable {
    private int errorCode;
    private String errorMessage;
}