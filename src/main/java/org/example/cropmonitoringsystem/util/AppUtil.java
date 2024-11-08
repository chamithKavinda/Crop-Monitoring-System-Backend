package org.example.cropmonitoringsystem.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.UUID;

public class AppUtil {
    public static String createVehicleCode(){return "Vehicle-"+ UUID.randomUUID();}
    public static String createEquipmentId(){return "Equipment-"+ UUID.randomUUID();}
    public static String createCropId(){return "Crop-"+ UUID.randomUUID();}
    public static String toBase64CropImage(MultipartFile CropImage) throws IOException {
        return Base64.getEncoder().encodeToString(CropImage.getBytes());
    }
}
