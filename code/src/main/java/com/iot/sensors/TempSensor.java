package com.iot.sensors;
/*
Author: Arjun Pandya
Date: 2018-07-09
Purpose: This class represents Temperature sensor.
 */

import org.bson.Document;
import java.util.StringTokenizer;

public class TempSensor {
    
    private int sensorId;
    private String clockTime;
    private double airTemp;
    private double windSpeed;
    private double surfaceTemp;
    private double latitude;
    private double longitude;
    public TempSensor(){
    }
    public TempSensor(int id, String clockTime, double airTemp,
                    double windSpeed, double surfaceTemp, double latitude, double longitude )
    {
        this.sensorId = id;
        this.clockTime = clockTime;
        this.airTemp = airTemp;
        this.windSpeed = windSpeed;
        this.surfaceTemp = surfaceTemp;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void parseString (String csvStr)
    {
        StringTokenizer st = new StringTokenizer(csvStr,",");
        sensorId=Integer.parseInt(st.nextToken());
        clockTime = st.nextToken();
        airTemp = Double.parseDouble(st.nextToken());
        windSpeed = Double.parseDouble(st.nextToken());
        surfaceTemp = Double.parseDouble(st.nextToken());
        latitude = Double.parseDouble(st.nextToken());
        longitude = Double.parseDouble(st.nextToken());
    }
// Properties

    public int getsensorId() {
        return sensorId;
    }
    public void setsensorId(int sensorId) {
        this.sensorId = sensorId;
    }
    public String getclockTime() {
        return clockTime;
    }
    public void setClockTime(String clockTime) {
        this.clockTime = clockTime;
    }

    public double getAirTemp() {
        return airTemp;
    }
    public void setAirTemp(double airTemp) {
        this.airTemp = airTemp;
    }
    public double getWindSpeed() {
        return windSpeed;
    }
    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }
    public double getsurfaceTemp() {
        return surfaceTemp;
    }
    public void setsurfaceTemp(double surfaceTemp) {
        this.surfaceTemp = surfaceTemp;
    }
    public double getlatitude() {
        return latitude;
    }
    public void setlatitude(double latitude) {
        this.latitude = latitude;
    }
    public double getlongitude() {
        return longitude;
    }
    public void setlongitude(double longitude) {
        this.longitude = longitude;
    }

    public Document getTemperatureAsDocument() {
        Document tempDocument = new Document("sensorId", getsensorId())
                .append("clockTime", getclockTime())
                .append("airTemp", getAirTemp())
                .append("windSpeed", getWindSpeed())
                .append("surfaceTemp", getsurfaceTemp())
                .append("latitude", getlatitude())
                .append("longitude",getlongitude());
        return tempDocument;
    };
    @Override
    public String toString() {
        return "TempSensor{" +
                "sensorId=" + sensorId +
                "clockTime=" + clockTime +
                ", airTemp=" + airTemp + '\'' +
                ", windSpeed=" + windSpeed + '\'' +
                ", surfaceTemp=" + surfaceTemp + '\'' +
                ", latitude=" + latitude + '\'' +
                ", longitude=" + longitude + '\'' +
                '}';
    }

}

