package org.example.cropmonitoringsystem.service.impl;

import org.example.cropmonitoringsystem.dao.StaffDao;
import org.example.cropmonitoringsystem.dto.impl.StaffDTO;
import org.example.cropmonitoringsystem.entity.StaffEntity;
import org.example.cropmonitoringsystem.exception.DataPersistFailedException;
import org.example.cropmonitoringsystem.service.StaffService;
import org.example.cropmonitoringsystem.util.AppUtil;
import org.example.cropmonitoringsystem.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StaffServiceIMPL implements StaffService {
    @Autowired
    private StaffDao staffDao;
    @Autowired
    private Mapping mapping;
    @Override
    public void saveStaff(StaffDTO staffDTO) {
        staffDTO.setStaffId(AppUtil.createStaffId());
        var staffEntity = mapping.convertToStaffEntity(staffDTO);
        var savedStaff = staffDao.save(staffEntity);
        if (savedStaff == null){
            throw new DataPersistFailedException("Cannot save Staff");
        }
    }
}
