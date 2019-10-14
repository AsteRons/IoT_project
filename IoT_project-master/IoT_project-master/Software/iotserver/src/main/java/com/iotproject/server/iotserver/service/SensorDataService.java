package com.iotproject.server.iotserver.service;

import java.util.List;
import com.iotproject.server.iotserver.model.SensorData;

public interface SensorDataService {

    public List<SensorData> findAll();

    public SensorData findById(int theId);

    public void save(SensorData theSensorData);

    public void deleteById(int theId);
}
