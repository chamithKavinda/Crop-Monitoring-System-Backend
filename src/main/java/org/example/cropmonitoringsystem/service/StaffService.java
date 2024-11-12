package org.example.cropmonitoringsystem.service;

import org.example.cropmonitoringsystem.customObj.StaffResponse;
import org.example.cropmonitoringsystem.dto.impl.StaffDTO;

import java.util.List;

public interface StaffService {
    void saveStaff(StaffDTO staffDTO);
    List<StaffDTO> getAllStaffs();
    StaffResponse getSelectedStaff(String staffId);
    void updateStaff(String staffId, StaffDTO staffDTO);
}
