package org.example.cropmonitoringsystem.dao;

import org.example.cropmonitoringsystem.entity.EquipmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentDao extends JpaRepository<EquipmentEntity,String> {
}