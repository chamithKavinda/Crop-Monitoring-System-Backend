package org.example.cropmonitoringsystem.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.UUID;

public class AppUtil {
    public static String createVehicleCode(){return "Vehicle-"+ UUID.randomUUID();}
    public static String createEquipmentId(){return "Equipment-"+ UUID.randomUUID();}
    public static String createCropId(){return "Crop-"+ UUID.randomUUID();}
    public static String createFieldId(){return "Field-"+ UUID.randomUUID();}
    public static String createStaffId(){return "Staff-"+ UUID.randomUUID();}

    public static String toBase64CropImage(MultipartFile cropImage) throws IOException {
        if (cropImage == null || cropImage.isEmpty()) {
            return null;
        }
        return Base64.getEncoder().encodeToString(cropImage.getBytes());
    }

    public static String toBase64FieldImage1(MultipartFile fieldImage1) throws IOException{
        if (fieldImage1 == null || fieldImage1.isEmpty()){
            return null;
        }
        return Base64.getEncoder().encodeToString(fieldImage1.getBytes());
    }

    public static String toBase64FieldImage2(MultipartFile fieldImage2) throws IOException{
        if (fieldImage2 == null || fieldImage2.isEmpty()){
            return null;
        }
        return Base64.getEncoder().encodeToString(fieldImage2.getBytes());
    }


}
