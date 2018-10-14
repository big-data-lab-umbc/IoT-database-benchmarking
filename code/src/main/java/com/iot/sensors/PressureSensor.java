package com.iot.sensors;
/*
Author: Arjun Pandya
Date: 2018-07-09
Purpose: This class represents Pressure sensor.
 */
import org.bson.Document;
import java.util.StringTokenizer;

public class PressureSensor {

    private int sensorId;
    private String clockTime;
    private double altitude;
    private double pressure;
    private double temperature;
    private double latitude;
    private double longitude;

    public PressureSensor(){
    }
    public PressureSensor(int id, String clockTime, double altitude, double pressure, double temperature,
                      double latitude, double longitude )
    {
        this.sensorId = id;
        this.clockTime = clockTime;
        this.altitude = altitude;
        this.pressure = pressure;
        this.temperature = temperature;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void parseString (String csvStr)
    {
        StringTokenizer st = new StringTokenizer(csvStr,",");
        sensorId=Integer.parseInt(st.nextToken());
        clockTime = st.nextToken();
        altitude = Double.parseDouble(st.nextToken());
        pressure = Double.parseDouble(st.nextToken());
        temperature = Double.parseDouble(st.nextToken());
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
    public double getaltitude() {
        return altitude;
    }
    public void setaltitude(double altitude) {
        this.altitude = altitude;
    }
    public double getpressure() {
        return pressure;
    }
    public void setpressure(double pressure) {
        this.pressure = pressure;
    }
    public double gettemperature() {
        return temperature;
    }
    public void settemperature(double temperature) {
        this.temperature = temperature;
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

    public Document getPressureAsDocument() {
        Document tempDocument = new Document("sensorId", getsensorId())
                .append("clockTime", getclockTime())
                .append("altitude",getaltitude())
                .append("pressure", getpressure())
                .append("temperature", gettemperature())
                .append("latitude", getlatitude())
                .append("longitude", getlongitude());
        return tempDocument;
    };
    @Override
    public String toString() {
        return "PressureSensor{" +
                "sensorId=" + sensorId +
                ", clockTime='" + clockTime + '\'' +
                ", altitude='" + altitude + '\'' +
                ", pressure=" + pressure + '\'' +
                ", temperature=" + temperature + '\'' +
                ", latitude=" + latitude + '\'' +
                ", longitude=" + longitude + '\'' +
                '}';
    }

}

