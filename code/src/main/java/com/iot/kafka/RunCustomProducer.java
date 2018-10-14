package com.iot.kafka;
/*
***************** An Open Source Benchmarking Platform *****************
        * This code was developed as part of research conducted by UMBC Ph.D. students,
        * to benchmark new generation of databases for IoT data.
        * This is an Open Source Benchmarking tool which can be used freely.
        *
        Author: Arjun Pandya
        Date: 2018-07-09
        Purpose: This program will execute CustomProducer for given number of sensors <specified as PRODUCER_COUNT in config.properties file>.

*/
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
public class RunCustomProducer {


    public static void main(String args[]) {
        Properties sysprop = new Properties();
        InputStream input = null;
        int producer_cnt = 0;
        String ttopic = null;
        String sensorType = null;

        try {
            input = new FileInputStream("resources/config.properties");
            // load a properties file
            sysprop.load(input);
            sensorType = sysprop.getProperty("SENSOR_TYPE");
            producer_cnt = Integer.parseInt(sysprop.getProperty("PRODUCER_COUNT"));
            ttopic = sysprop.getProperty("SENSOR_TOPIC");

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //     Loop for producers
        for(int i = producer_cnt; i > 0; i--) {
//            Creating producer for each sensor and providing different topic name
                CustomProducer pdr = new CustomProducer(sensorType, ttopic+"_"+i);
                pdr.start();
        }

    }
}
