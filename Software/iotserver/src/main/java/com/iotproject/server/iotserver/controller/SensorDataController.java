package com.iotproject.server.iotserver.controller;

import com.iotproject.server.iotserver.service.SensorDataService;
import com.iotproject.server.iotserver.model.SensorData;

import java.util.Date;
import java.util.TreeMap;
import java.util.List;
import java.util.Map;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/sensorData")
public class SensorDataController {


    private SensorDataService sensorDataService;

    // load sensorData data
    public SensorDataController(SensorDataService theSensorDataService) {
        sensorDataService = theSensorDataService;
    }

    // add mapping for "/list"

    @RequestMapping("/list")
    public String listSensorData(Model theModel){

        List<SensorData> theSensorData = sensorDataService.findAll();
        theModel.addAttribute("sensorData", theSensorData);
        return "sensorData/list-sensorData";
    }

    @GetMapping("/gaugeSensorData_1")
    public String graphSensorDataPointer(Model theModel){
        List<SensorData> theSensorDataPointer = sensorDataService.findAll();
        SensorData valuePointers = theSensorDataPointer.get(theSensorDataPointer.size() - 1);
        int valuePointer = valuePointers.getNtuValue();
        theModel.addAttribute("theSensorDataPointers", valuePointer);
        System.out.println(valuePointer);
        return "sensorData/gauge-sensorData";
    }


    @GetMapping("/graphSensorData_1")
    public String graphSensorData(Model theModel){

        List<SensorData> theSensorData = sensorDataService.findAll();
        Map<Date, Integer> sensorDataMaps = new TreeMap<>();

        for(SensorData sensorDataMap : theSensorData)
        {
            sensorDataMaps.put(sensorDataMap.getTimestamp(), sensorDataMap.getNtuValue());
        }
        theModel.addAttribute("sensorDataMaps", sensorDataMaps);
        return "sensorData/graph-sensorData";
    }

    @GetMapping("/showMyLoginPage")
    public String showMyLoginPage() {

        return "sensorData/plain-login";

    }

    @RequestMapping("/showMyMainPage")
    public String showMyMainPage() {

        return "sensorData/main-sensorData";

    }

}
