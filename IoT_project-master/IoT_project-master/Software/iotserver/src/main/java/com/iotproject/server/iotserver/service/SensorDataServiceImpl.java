package com.iotproject.server.iotserver.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iotproject.server.iotserver.dao.SensorDataRepository;
import com.iotproject.server.iotserver.model.SensorData;

@Service
public class SensorDataServiceImpl implements SensorDataService{

    private SensorDataRepository sensorDataRepository;

    @Autowired
    public SensorDataServiceImpl(SensorDataRepository theSensorDataRepository) {
        sensorDataRepository = theSensorDataRepository;
    }

    @Override
    public List<SensorData> findAll() {
        return sensorDataRepository.findAllByOrderByDeviceIDAsc();
    }

    @Override
    public SensorData findById(int theId) {
        Optional<SensorData> result = sensorDataRepository.findById(theId);

        SensorData theSensorData = null;

        if (result.isPresent()) {
            theSensorData = result.get();
        }
        else {
            // we didn't find the employee
            throw new RuntimeException("Did not find employee id - " + theId);
        }
        return theSensorData;
    }

    @Override
    public void save(SensorData theSensorData) {
        sensorDataRepository.save(theSensorData);
    }

    @Override
    public void deleteById(int theId) {
        sensorDataRepository.deleteById(theId);
    }
}
