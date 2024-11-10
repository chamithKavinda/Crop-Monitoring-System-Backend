package org.example.cropmonitoringsystem.customObj.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.cropmonitoringsystem.customObj.FieldResponse;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FieldErrorResponse implements FieldResponse, Serializable {
    private int errorCode;
    private String errorMessage;
}
