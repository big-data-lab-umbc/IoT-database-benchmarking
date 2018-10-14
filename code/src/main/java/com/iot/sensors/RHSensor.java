package com.iot.sensors;
/*
Author: Arjun Pandya
Date: 2018-07-09
Purpose: This class represents Relative Humidity sensor.
 */
import java.util.StringTokenizer;
import org.bson.Document;

public class RHSensor {
    private int sensorId;
    private String clockTime;
    private String rainAcc;
    private double airTemp;
    private String qualityFlag;
    private double windSpeed;
    private double rHumidity;
    private int windDirection;
    private double latitude;
    private double longitude;

    public RHSensor(){

    }
    public RHSensor(int id, String clockTime, String rainAcc, double airTemp, String qualityFlag,
                        double windSpeed, double rHumidity, int windDirection )
    {
        this.sensorId = id;
        this.clockTime = clockTime;
        this.rainAcc = rainAcc;
        this.airTemp = airTemp;
        this.qualityFlag = qualityFlag;
        this.windSpeed = windSpeed;
        this.rHumidity = rHumidity;
        this.windDirection = windDirection;
        this.latitude = latitude;
        this.longitude = longitude;

    }

    public void parseString (String csvStr)
    {
        StringTokenizer st = new StringTokenizer(csvStr,",");
        sensorId=Integer.parseInt(st.nextToken());
        clockTime = st.nextToken();
        rainAcc = st.nextToken();
        airTemp = Double.parseDouble(st.nextToken());
        qualityFlag = st.nextToken();
        windSpeed = Double.parseDouble(st.nextToken());
        rHumidity = Double.parseDouble(st.nextToken());
        windDirection = Integer.parseInt(st.nextToken());
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
    public String getrainAcc() {
        return rainAcc;
    }
    public void setrainAcc(String rainAcc) {
        this.rainAcc = rainAcc;
    }
    public double getAirTemp() {
        return airTemp;
    }
    public void setAirTemp(double airTemp) {
        this.airTemp = airTemp;
    }
    public String getQualityFlag() {
        return qualityFlag;
    }
    public void setQualityFlag(String qualityFlag) {
        this.qualityFlag = qualityFlag;
    }
    public double getWindSpeed() {
        return windSpeed;
    }
    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }
    public double getRHumidity() {
        return rHumidity;
    }
    public void setRHumidity(double rHumidity) {
        this.rHumidity = rHumidity;
    }
    public int getWindDirection() {
        return windDirection;
    }
    public void setWindDirection(int windDirection) {
        this.windDirection = windDirection;
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

    public Document getRhAsDocument() {
        Document rhDocument = new Document("id", getsensorId())
                .append("clockTime", getclockTime())
                .append("airTemp", getAirTemp())
                .append("qualityFlag", getQualityFlag())
                .append("windSpeed", getWindSpeed())
                .append("rHumidity", getRHumidity())
                .append("windDirection", getWindDirection())
                .append("latitude", getlatitude())
                .append("longitude", getlongitude());
        return rhDocument;
    };
    @Override
    public String toString() {
        return "RHSensor{" +
                "sensorId=" + sensorId +
                ", clockTime=" + clockTime + '\'' +
                ", rainAcc='" + rainAcc + '\'' +
                ", airTemp=" + airTemp + '\'' +
                ", qualityFlag=" + qualityFlag + '\'' +
                ", windSpeed=" + windSpeed + '\'' +
                ", rHumidity=" + rHumidity + '\'' +
                ", windDirection=" + windDirection + '\'' +
                ", latitude=" + latitude + '\'' +
                ", longitude=" + longitude + '\'' +
                '}';
    }

}


