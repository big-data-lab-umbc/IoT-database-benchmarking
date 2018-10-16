<H1> How to use this framework</H1>

<img width="969" alt="screen shot 2018-10-15 at 10 12 06 pm" src="https://user-images.githubusercontent.com/34160872/46988611-71bbc300-d0c7-11e8-9699-d9de30948c1a.png">

This framework is divided into 3 tiers: 
1. Sensor Data Producer
1. Stream Messaging Server
1. Streaming Message Consumer/ Database Clusters
<br>
<br>
Following are the steps to setup each tier, the steps shown here were used for our test environment, user can configure their environment as per their choice i.e. configure each tier on multiple machines or on a single machine
<br>
<br>
<I><h2> 1. Setup Sensor Data Producer</I></h2><br>
	            a. Download the code and deploy it on your desired server<br><br>
<I><h2> 2. Setup Messaging Server (Apache Kafka) </h2>
use following link for detailed tutorial on how to configure Kafka Server</I></h2></B>	 https://kafka.apache.org/quickstart <br><br>
<I><h2> 3. Setup Database Cluster with Message Consumer</B></I></h2><br>
<BR>
	    <h3>3.1. MongoDB Cluster Setup</h3>
		a. Download and Install MongoDB 3.XX community version<br>
			  https://docs.mongodb.com/manual/tutorial/install-mongodb-on-ubuntu/<br>
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
a. Download VoltDB community version<br>
b. Extract the VoltDB directory:
	
	tar -xzf voltdb-ent-latest.tar.gz
				
c. Change directory to :
				
    ./voltdb/bin/
				
d. Execute :

    ./voltdb init --config=./deployment.xml

Follow above steps for each VoltDB node that you want as part of your cluster.<br>
	<br>
  <br>
<I> Note: You can use the deployment.xml file from our repository, make sure to change Kafka importer detail in it. Also you can change the number of host you need for your cluster.	</I>	
