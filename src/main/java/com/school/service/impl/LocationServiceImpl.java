package com.school.service.impl;

import com.school.dtoObject.Location;
import com.school.repository.LocationRepository;
import com.school.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationRepository repository;

    @Override
    public Location save(Location location) {
        return repository.save(location);
    }

    @Override
    public Location findOne(Integer locationId) {
        return repository.findById(locationId).orElse(null);
    }

    @Override
    public Location findByClassroomLocation(String classroomLocation) {
        return repository.findByClassroomLocation(classroomLocation);
    }

    @Override
    public List<Location> findAll() {
        return repository.findAll();
    }
}
