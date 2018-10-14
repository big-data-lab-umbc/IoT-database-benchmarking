package com.iot.dbs.mogodb;

/*
***************** An Open Source Benchmarking Platform *****************
* This code was developed as part of research conducted by UMBC Ph.D. students,
* to benchmark new generation of databases for IoT data.
*
* This is an Open Source Benchmarking tool, which can be used freely.
*
Author: Arjun Pandya
Date: 2018-07-09
Purpose: This program will continuously looks for messages posted under the topic <specified as SENSOR_TOPIC in configuration file>
         and stores it into MongoDB database <specified as MONGO_DATABASE in configuration file>.
         collection <specified as MONGO_COLLECTION in configuration file>.
*/

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class RunImporter {

    public static void main(String args[]) {
        Properties sysprop = new Properties();
        InputStream input = null;
        int partition = 0;
        int producer_cnt = 0;
        String topic = null;
        String mongoDB = null;
        String mongoCol = null;
        String sensorType = null;

        try {
            input = new FileInputStream("resources/config.properties");
            // load a properties file
            sysprop.load(input);
            sensorType = sysprop.getProperty("SENSOR_TYPE");
            mongoDB = sysprop.getProperty("MONGO_DATABASE");
            mongoCol = sysprop.getProperty("MONGO_COLLECTION");
            producer_cnt = Integer.parseInt(sysprop.getProperty("PRODUCER_COUNT"));
            partition = Integer.parseInt(sysprop.getProperty("PARTITION_COUNT"));
            topic = sysprop.getProperty("SENSOR_TOPIC");

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
            //   Loop for partitions per producer
            for(int j = 0; j < partition; j++) {
                Importer imp = new Importer(sensorType, topic+"_"+i, j, mongoDB, mongoCol);
                imp.start();
            }
        }

    }
}