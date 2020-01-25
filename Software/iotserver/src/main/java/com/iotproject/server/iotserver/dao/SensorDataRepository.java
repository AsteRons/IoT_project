package com.iotproject.server.iotserver.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.iotproject.server.iotserver.model.SensorData;


/**
 * Interfejs do komunikacji z bazą danych
 * Do pobierania danych została wykorzystany Spring Data z implementacją klasy JpaRepository
 * Konfiguracja połączenia z bazą została umieszczona w pliku iotserver\src\main\resources\application.properties
 * Mapuje oraz wykonuje operacje na daanych za pomocą adnotacji umieszczonych na danych w klasie Model.
 * findAllByOrderByIdAsc - lista określająca po czym zostanie wykonywana komunikacja z bazą ( w naszym przypadku lista
 * pobierajaca obiekt za pomocą Id)
 */
public interface SensorDataRepository  extends JpaRepository<SensorData, Integer>{

    // add a method to sort by last name
    public List<SensorData> findAllByOrderByIdAsc();

}
