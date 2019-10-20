package com.iotproject.server.iotserver.controller;

import com.iotproject.server.iotserver.service.SensorDataService;
import com.iotproject.server.iotserver.model.SensorData;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/sensorData")
public class SensorDataController {


    private SensorDataService sensorDataService;

    // load sensorData data
    public SensorDataController(SensorDataService theSensorDataService) {
        sensorDataService = theSensorDataService;
    }

    // add mapping for "/list"

    @GetMapping("/list")
    public String listSensorData(Model theModel){

        List<SensorData> theSensorData = sensorDataService.findAll();
        theModel.addAttribute("sensorData", theSensorData);

        return "sensorData/list-sensorData";
    }

    @GetMapping("/graphSensorData_1")
    public String graphSensorData(Model theModel){

        List<SensorData> theSensorData = sensorDataService.findAll();
        Map<Date, Integer> sensorDataMaps = new LinkedHashMap<>();

        for(SensorData sensorDataMap : theSensorData)
        {
            sensorDataMaps.put(sensorDataMap.getTimestamp(), sensorDataMap.getNtuValue());
        }

        theModel.addAttribute("sensorDataMaps", sensorDataMaps);

        return "sensorData/graph-sensorData";
    }

}
