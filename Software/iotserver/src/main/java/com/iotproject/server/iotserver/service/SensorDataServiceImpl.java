package com.iotproject.server.iotserver.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iotproject.server.iotserver.dao.SensorDataRepository;
import com.iotproject.server.iotserver.model.SensorData;

/**
 * Implementasja metod do wykonywania iperasji na danych wbazie
 */
@Service
public class SensorDataServiceImpl implements SensorDataService{

    private SensorDataRepository sensorDataRepository;

    @Autowired
    public SensorDataServiceImpl(SensorDataRepository theSensorDataRepository) {
        sensorDataRepository = theSensorDataRepository;
    }

    /**
     * Pobiera  wszystkie obiekty z bazy i zapisuje je do List
     * @return - zwraca liste wszystkich  obiektów z bazy
     */
    @Override
    public List<SensorData> findAll() {
        return sensorDataRepository.findAllByOrderByIdAsc();
    }

    /**
     * Pobiera obiekt z bazy o określonym Id
     * @param theId - id poszukiwanego obiektu
     * @return - zwraca znaleziony obiekt
     */
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

    /**
     * Zapisuje obiekt do bazy danych
     * @param theSensorData  obiekt danych do zapisu
     */
    @Override
    public void save(SensorData theSensorData) {
        sensorDataRepository.save(theSensorData);
    }

    /**
     * Usuwa z bazy danych obiekt o okreslonym Id
     * @param theId  Id obiektu który ma być usunięty z bazy
     */
    @Override
    public void deleteById(int theId) {
        sensorDataRepository.deleteById(theId);
    }
}
