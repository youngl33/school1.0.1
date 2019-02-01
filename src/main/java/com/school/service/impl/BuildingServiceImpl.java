package com.school.service.impl;

import com.school.dtoObject.Building;
import com.school.repository.BuildingRepository;
import com.school.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuildingServiceImpl implements BuildingService {

    @Autowired
    private BuildingRepository repository;

    @Override
    public Building findOne(Integer buildingId) {
        return repository.findById(buildingId).orElse(null);
    }

    @Override
    public List<Building> findAll() {
        return repository.findAll();
    }

    @Override
    public Building save(Building building){
        return repository.save(building);
    }

    @Override
    public Building findByBuildingName(String buildingName){
        return repository.findByBuildingName(buildingName);
    }

    @Override
    public List<Building> findByLocationId(Integer locationId) {
        return repository.findByLocationId(locationId);
    }

}
