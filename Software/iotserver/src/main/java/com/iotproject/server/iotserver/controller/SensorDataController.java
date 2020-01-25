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


/**
 * Klasa do implementacji logiki serwera
 */
@Controller
@RequestMapping("/sensorData")
public class SensorDataController {

    /**
     * Implementacja serwisu do wykonywania operacji  na danych z bazy
     */
    private SensorDataService sensorDataService;

    /**
     * Domyślny konstruktor klasy SensorDataController
     * @param theSensorDataService  serwisu do wykonywania operacji  na danych z bazy
     */
    // load sensorData data
    public SensorDataController(SensorDataService theSensorDataService) {
        sensorDataService = theSensorDataService;
    }

    // add mapping for "/list"

    /**
     *  Implementacja logiki dla strony html z tabelą
     * @param theModel  pobiera dane aby były widoczne na stronie html
     * @return  zwraca strone html z tabelą danych
     */
    @RequestMapping("/list")
    public String listSensorData(Model theModel){

        List<SensorData> theSensorData = sensorDataService.findAll();
        theModel.addAttribute("sensorData", theSensorData);
        return "sensorData/list-sensorData";
    }

    /**
     * Implementacja logiki dla strony html z wskaźnikiem
     * @param theModel  pobiera dane aby były widoczne na stronie html
     * @return  zwraca strone html z wskaźnikiem
     */
    @GetMapping("/gaugeSensorData_1")
    public String graphSensorDataPointer(Model theModel){
        List<SensorData> theSensorDataPointer = sensorDataService.findAll();
        SensorData valuePointers = theSensorDataPointer.get(theSensorDataPointer.size() - 1);
        int valuePointer = valuePointers.getNtuValue();
        theModel.addAttribute("theSensorDataPointers", valuePointer);
        System.out.println(valuePointer);
        return "sensorData/gauge-sensorData";
    }

    /**
     * Implementacja logiki dla wykresu
     * @param theModel  pobiera dane aby były widoczne na stronie html
     * @return  zwraca strone html z wykresem
     */
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

    /**
     * Implementacja logiki logowania
     * @return  zwraca strone html z logowaniem
     */
    @GetMapping("/showMyLoginPage")
    public String showMyLoginPage() {

        return "sensorData/plain-login";

    }

    /**
     * Implementacja logiki strony głównej
     * @return  zwraca strone html z strony głównej
     */
    @RequestMapping("/showMyMainPage")
    public String showMyMainPage() {

        return "sensorData/main-sensorData";

    }

}
