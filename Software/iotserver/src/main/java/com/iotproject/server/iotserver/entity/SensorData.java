package com.iotproject.server.iotserver.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sensorData")
public class SensorData {

    // define fields
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID")
    private int id;

    @Column(name="deviceID")
    private int deviceID;

    @Column(name="ntuValue")
    private String ntuValue;

    @Column(name="timestamp")
    private Date timestamp;


    // define constructors

    public SensorData(){

    }
    public SensorData(int id, int deviceID, String ntuValue, Date timestamp){
        this.id = id;
        this.deviceID = deviceID;
        this.ntuValue = ntuValue;
        this.timestamp = (Date)timestamp.clone();
    }

    public SensorData(int deviceID, String ntuValue, Date timestamp){
        this.deviceID = deviceID;
        this.ntuValue = ntuValue;
        this.timestamp = (Date)timestamp.clone();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(int deviceID) {
        this.deviceID = deviceID;
    }

    public String getNtuValue() {
        return ntuValue;
    }

    public void setNtuValue(String ntuValue) {
        this.ntuValue = ntuValue;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = (Date)timestamp.clone();
    }

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
