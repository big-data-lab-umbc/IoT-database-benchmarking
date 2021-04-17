<H1> How to use this framework</H1>

<img width="969" alt="image" src="https://user-images.githubusercontent.com/34160872/49715100-52e83180-fc1d-11e8-9a7a-60d3a210fa84.png">

This framework is divided into 3 tiers: 
1. IoT Data Generator
1. Message Streaming Middleware
1. Database Clusters
<br>
<br>
Following are the steps to setup each tier, the steps shown here were used for our test environment, user can configure their environment as per their choice i.e. configure each tier on multiple machines or on a single machine
<br>
<br>
<I><h2> 1. Setup IoT Data Generator & Message Producer</I></h2><br>
	            a. Download the code and deploy it on your desired server<br><br>
		    b. Edit Section 1 and 3 of configuration in config.properties file: 
		    
    # ********** SECTION 1 **********
    ########## CustomProducer configurations ##########
    # Message type should be used to define the kafka message format
    # use csv for CSV message format
    # use json for JSON message format
    MESSAGE_TYPE = csv
    # BROKER_LIST can be used to define the number of kafka brokers to send messages, include server ip and port number
    # for ex. 1.0.0.27:9092
    BROKER_LIST = 1.0.0.27:9092
    # Following properties define the topic for each sensor under which you want to produce messages for each sensor
    SENSOR_TOPIC = MY_TOPIC
    .
    .
    .
    # ********** SECTION 3 **********
    ########## Execution properties ##########
    #PRODUCER_COUNT is the number of procedure we want to run for a sensor, general setup creates 1 producer for 1 sensor.
    PRODUCER_COUNT = 10
    # EXEC_TIME defines the number of hours for which you want to produce the messages, provide the value in secs,
    # for 1 minute provide 60, which is 60 seconds
    # for 1 hour provide 3600, which is 60 seconds * 60 minutes
    # for 8 hours 28800 which is 60 seconds * 60 minutes * 8 hours
    EXEC_TIME = 10
    # RECS_PER_SEC defines number of messages per second you want to produce for each sensor
    RECS_PER_SEC = 100

    # SENSOR_TYPE defines for which sensor you want to produce messages,
    # ALL for all 3 sensors
    # RH for Relative Humidity Sensor
    # P for Pressure Sensor
    # T for Temperature Sensor
    SENSOR_TYPE = T

<I><h2> 2. Setup Messaging Server (Apache Kafka) </h2>
Refer [Apache Kafka](https://kafka.apache.org/quickstart), for detailed tutorial on how to install and configure Apache Kafka Server</I></h2></B><br><br>
<I><h2> 3. Setup Database Cluster with Message Consumer</B></I></h2><br>
<BR>
	    <h3>3.1. MongoDB Cluster Setup</h3>
		a. Download and Install MongoDB 3.XX community version<br>
			  [Installing MongoDB](https://docs.mongodb.com/manual/tutorial/install-mongodb-on-ubuntu/)<br>
		b. Set hostname on each server you want as part of MongoDB shard including Config server and Mongos Server<br>
		c. Edit config file for Mongo node /etc/mongod.conf and update following entries:
				
    #network
    net:
    port: 27017
    bindIp: 0.0.0.0 
				
    #replication:
    replication:
    replSetName: "rs0"     

d. Edit config file for Mongo Config server /etc/mongod.conf and update following entries:
			
    #network
    net:
    port: 27019
    bindIp: 0.0.0.0 
				
    #replication:
    replication:
    replSetName: "configReplSet"   
e. Edit configuration file for each mongos, remove storage section from the config file:
    
	  # systemLog:
	  #destination: file
	  #logAppend: true
	  #path: /var/log/mongodb/mongod.log

	# network interfaces
	net:
	  port: 27017
	  bindIp: 0.0.0.0
	#sharding:
	sharding:
	   configDB: configReplSet/mongocs01:27019

				
<BR>
<h3> 3.2. VoltDB Cluster Setup</h3><br>
a. Download VoltDB community version <br>
	
b. Extract the VoltDB directory:
	
	tar -xzf voltdb-ent-latest.tar.gz
				
c. Change directory to :
				
    ./voltdb/bin/
				
d. Execute :

    ./voltdb init --config=./deployment.xml

Follow above steps for each VoltDB node that you want as part of your cluster. For additional help refer [VoltDB Documentation](https://www.voltdb.com/resources/get-started-voltdb/)<br>
	<br>
  <br>
<I> Note: You can use the deployment.xml file from our repository, make sure to change Kafka importer detail in it. Also you can change the number of host you need for your cluster.	</I>	

# Publication
If you make use of this code in your own work, please cite our paper:
Arjun Pandya, Chaitanya Kulkarni, Kunal Mali, and Jianwu Wang. "An Open Source Cloud-Based NoSQL and NewSQL Database Benchmarking Platform for IoT Data." In International Symposium on Benchmarking, Measuring and Optimization, pp. 65-77. Springer, Cham, 2018.
