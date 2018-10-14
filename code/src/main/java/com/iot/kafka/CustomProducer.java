package com.iot.kafka;
/*
***************** An Open Source Benchmarking Platform *****************
        * This code was developed as part of research conducted by UMBC Ph.D. students,
        * to benchmark new generation of databases for IoT data.
        * This is an Open Source Benchmarking tool which can be used freely.
        *
        Author: Arjun Pandya
        Date: 2018-07-09
        Purpose: This program will generate and send sensor data as Kafka message for a given time window <specified as EXEC_TIME in config.properties file>,
                    at a given velocity <specified as RECS_PER_SEC in config.properties file>,
                    in specified format <specified as MESSAGE_TYPE in config.properties file>.

*/
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iot.sensors.TempSensor;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CustomProducer implements Runnable {
    private Thread t;
    private String sensorType;
    private String brokerList = null;
    private String topic = null;
    private String msgType = null;
    private List<String> seeds = new ArrayList<String>();
    private Properties sysprop = new Properties();
    InputStream input = null;
    private int execTime = 0;
    private int recPersec = 0;
    public CustomProducer(String p_sensorType, String p_topic){
        sensorType = p_sensorType;
        topic = p_topic;

        try {
            input = new FileInputStream("resources/config.properties");
            // load a properties file
            sysprop.load(input);
            brokerList = sysprop.getProperty("BROKER_LIST");
            execTime = Integer.parseInt(sysprop.getProperty("EXEC_TIME"));
            recPersec = Integer.parseInt(sysprop.getProperty("RECS_PER_SEC"));
            msgType = sysprop.getProperty("MESSAGE_TYPE");
            seeds.add(brokerList);
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
    }
    public void run(){
        System.out.println("Process Starting at "+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        for (int t = 1; t < execTime; t++) {
            for (int i = 0; i < recPersec; i++) {

                SendMessage(topic);
            }
            try {
                Thread.sleep(1000); // to ensure messages per second count
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void start () {
        if (t == null) {
            t = new Thread (this, sensorType);
            t.start ();
        }
    }
    public String GenerateMessage(){
        Random r = new Random();
        StringBuffer sb = new StringBuffer();
        Double latitude =0.0;
        Double longitude =0.0;
        sb.append(r.nextInt(100));  // Sensor_id
        sb.append(",");
        sb.append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))); // Clock time
        sb.append(",");
        sb.append(r.nextLong()); // Altitude
        sb.append(",");
        sb.append(r.nextLong()); // Pressure
        sb.append(",");
        sb.append(r.nextDouble()); // Temperature
        sb.append(",");
        sb.append(latitude); // Latitude
        sb.append(",");
        sb.append(longitude); // Longitude
        return sb.toString();
    }
    public void SendMessage(String ptopic){

        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.ByteArraySerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.connect.json.JsonSerializer");
        org.apache.kafka.clients.producer.Producer producer = new KafkaProducer(props);
        ObjectMapper mapper = new ObjectMapper();
        TempSensor tempSensor = new TempSensor(); // Temperature
        tempSensor.parseString(GenerateMessage());
        // Sending messages as JSON object
        if (msgType.equals("json")) {
            JsonNode tNode = mapper.valueToTree(tempSensor);
            ProducerRecord<String, JsonNode> t = new ProducerRecord<String, JsonNode>(ptopic, tNode);
            producer.send(t);
        }
        // Sending messages as Comma separated values
        if (msgType.equals("csv")) {
            ProducerRecord<String, String> t = new ProducerRecord<String, String>(ptopic, tempSensor.toString());
            producer.send(t);
        }

    }
}
