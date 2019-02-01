package com.school.repository;

import com.school.dtoObject.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location,Integer> {

    Location findByClassroomLocation(String classroomLocation);
}
