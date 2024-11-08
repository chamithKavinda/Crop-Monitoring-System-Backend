package org.example.cropmonitoringsystem.util;

import java.util.UUID;

public class AppUtil {
    public static String createVehicleCode(){return "Vehicle-"+ UUID.randomUUID();}
    public static String createEquipmentId(){return "Equipment-"+ UUID.randomUUID();}
    public static String createCropId(){return "Crop-"+ UUID.randomUUID();}
}
