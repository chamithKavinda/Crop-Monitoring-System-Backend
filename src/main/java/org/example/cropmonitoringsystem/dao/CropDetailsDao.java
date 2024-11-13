package org.example.cropmonitoringsystem.dao;

import org.example.cropmonitoringsystem.dto.impl.CropDetailsDTO;
import org.example.cropmonitoringsystem.entity.CropDetailsEntity;
import org.example.cropmonitoringsystem.entity.CropEntity;
import org.example.cropmonitoringsystem.entity.FieldEntity;
import org.example.cropmonitoringsystem.entity.StaffEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CropDetailsDao extends JpaRepository<CropDetailsEntity,String> {

}
