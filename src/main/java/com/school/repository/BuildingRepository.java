package com.school.repository;

import com.school.dtoObject.Building;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuildingRepository extends JpaRepository<Building,Integer> {
    Building findByBuildingName(String buildingName);
}
