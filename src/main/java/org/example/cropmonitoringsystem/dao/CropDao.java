package org.example.cropmonitoringsystem.dao;

import org.example.cropmonitoringsystem.entity.CropEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CropDao extends JpaRepository<CropEntity,String> {
}
