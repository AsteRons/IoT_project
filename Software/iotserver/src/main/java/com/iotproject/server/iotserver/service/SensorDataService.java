package com.iotproject.server.iotserver.service;

import java.util.List;
import com.iotproject.server.iotserver.model.SensorData;

/**
 * Deklaracja podstawowych metod jakie będą wykorzstane przy wykonywaniu operasji na deanych w bazie
 */
public interface SensorDataService {

    public List<SensorData> findAll();

    public SensorData findById(int theId);

    public void save(SensorData theSensorData);

    public void deleteById(int theId);
}
