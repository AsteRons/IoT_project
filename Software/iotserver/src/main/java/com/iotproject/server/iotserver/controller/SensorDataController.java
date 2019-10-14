package com.iotproject.server.iotserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iotproject.server.iotserver.service.SensorDataService;

@Controller
@RequestMapping("/employees")
public class SensorDataController {

    private SensorDataService sensorDataService;

    // load sensorData data
    public SensorDataController(SensorDataService theSensorDataService) {
        sensorDataService = theSensorDataService;
    }
}
