package com.iotproject.server.iotserver.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.iotproject.server.iotserver.model.SensorData;

public interface SensorDataRepository  extends JpaRepository<SensorData, Integer>{

    // add a method to sort by last name
    public List<SensorData> findAllByOrderByIdAsc();

}
