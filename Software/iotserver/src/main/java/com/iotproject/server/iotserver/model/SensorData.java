package com.iotproject.server.iotserver.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Createed by Maciej Rudnik on 12/10/2019
 * Klasa służy do zdefiniowania modelu danych zapisywanych w bazie danych.
 * Jest to podstawowa klasa opisu naszych danych
 */
@Entity
@Table(name="sensordata")
public class SensorData {

    /**
     * Pole definiujące id naszego obiektu (np. id w bazie)
     */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID")
    private int id;

    /**
     * Pole definiujące numer urządzenia, którego dotyczy ten obiekt
     */
    @Column(name="deviceID")
    public int deviceID;

    /**
     * Pole definiujące wartość pomiaru dokonanego przez urządzenie, którego dotyczy ten obiekt
     */
    @Column(name="ntuvalue")
    private int ntuValue;

    /**
     * Pole definiujące
     */
    @Column(name="timestamp")
    private Date timestamp;


    // define constructors

    /**
     * Konstruktor podstawowy
     */
    public SensorData(){

    }
    /**
     * Konstruktor z inicjujący obiekt z danymi
     * @param id - id urządzenia (np. id w bazie)
     * @param deviceID - numer urządzenia, którego dotyczy ten obiekt
     * @param ntuValue - wartość pomiaru dokonanego przez urządzenie, którego dotyczy ten obiekt
     * @param timestamp - wartość pomiaru dokonanego przez urządzenie, którego dotyczy ten obiekt
     */
    public SensorData(int id, int deviceID, int ntuValue, Date timestamp){
        this.id = id;
        this.deviceID = deviceID;
        this.ntuValue = ntuValue;
        this.timestamp = (Date)timestamp.clone();
    }
    /**
     * Konstruktor z inicjujący obiekt z danymi
     * @param deviceID - numer urządzenia, którego dotyczy ten obiekt
     * @param ntuValue - wartość pomiaru dokonanego przez urządzenie, którego dotyczy ten obiekt
     * @param timestamp - czas kiedy został dokonany pomiar dla obiektu
     */
    public SensorData(int deviceID, int ntuValue, Date timestamp){
        this.deviceID = deviceID;
        this.ntuValue = ntuValue;
        this.timestamp = (Date)timestamp.clone();
    }

    /**
     * getter dla Id
     * @return id - zwraca wartość id z obiektu
     */
    public int getId() {
        return id;
    }

    /**
     * setter dla Id
     * id - ustawia wartość id dla obiektu
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * getter dla DeviceID
     * @return deviceID - zwraca  numer urządzenia z obiektu
     */
    public int getDeviceID() {
        return deviceID;
    }

    /**
     * setter dla deviceID
     * deviceID - ustawia  numer urządzenia z obiektu
     */
    public void setDeviceID(int deviceID) {
        this.deviceID = deviceID;
    }

    /**
     * getter dla ntuValue
     * @return ntuValue - zwraca wartość pomiaru z obiektu
     */
    public int getNtuValue() {
        return ntuValue;
    }

    /**
     * setter dla ntuValue
     * ntuValue - ustawia wartość numeru urządzenia z obiektu
     */
    public void setNtuValue(int ntuValue) {
        this.ntuValue = ntuValue;
    }

    /**
     * getter dla timestamp
     * @return timestamp - zwraca  czas pomiaru dla obiektu
     */

    public Date getTimestamp() {
        return timestamp;
    }

    /**
     * setter dla timestamp
     * timestamp - ustawia czas pomiaru dla obiektu
     */
    public void setTimestamp(Date timestamp) {
        this.timestamp = (Date)timestamp.clone();
    }

    /**
     *
     * @return zwaraca opis klasy
     */
    @Override
    public String toString() {
        return "SensorData{" +
                "id=" + id +
                ", deviceID=" + deviceID +
                ", ntuValue='" + ntuValue + '\'' +
                ", timestamp=" + timestamp.toString() +
                '}';
    }
}
