package org.example.cropmonitoringsystem.customObj.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.cropmonitoringsystem.customObj.CropDetailsResponse;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CropDetailsErrorResponse implements CropDetailsResponse {
    private int errorCode;
    private String errorMessage;
}
