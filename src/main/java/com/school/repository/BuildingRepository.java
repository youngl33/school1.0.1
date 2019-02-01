package com.school.repository;

import com.school.dtoObject.Building;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BuildingRepository extends JpaRepository<Building,Integer> {
    Building findByBuildingName(String buildingName);

    List<Building> findByLocationId(Integer locationId);
}
