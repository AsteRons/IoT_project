package com.iotproject.server.iotserver.communication;

import com.iotproject.server.iotserver.model.SensorData;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CommunicationJSON {

    public void call_me() throws Exception {

        SensorData sensorData = new SensorData();
        String url = "http://api.ipinfodb.com/v3/ip-city/?key=d64fcfdfacc213c7ddf4ef911dfe97b55e4696be3532bf8302876c09ebd06b&ip=74.125.45.100&format=json";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        // optional default is GET
        con.setRequestMethod("GET");
        //add request header
        con.setRequestProperty("Content-length", "0");
        int responseCode = con.getResponseCode();



        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {

            response.append(inputLine);
        }

        JSONObject myResponse = new JSONObject(response.toString());

        sensorData.setId(myResponse.getInt( "ID"));
        sensorData.setDeviceID(myResponse.getInt( "deviceID"));
        sensorData.setNtuValue(myResponse.getInt( "ntuValue"));

        }

}
