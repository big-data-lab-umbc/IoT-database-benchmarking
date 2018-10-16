## IoT-database-benchmarking 
### How to use this framework  

This framework is divided into 3 tiers: 
1. Sensor Data Producer
1. Message Streaming Server
1. Streaming Message Consumer/ Database Clusters
<br>
<br>
Following are the steps to setup each tier, the steps shown here were used for our test environment, user can configure their environment as per their choice i.e. configure each tier on multiple machines or on a single machine
<br>
<br>
<I><B> 1. Setup Sensor Data Producer</I><br></B>
	            a. Download the code and deploy it on your desired server<br>
<I><B> 2. Setup Messaging Server (Apache Kafka), use following link for detailed tutorial on how to configure Kafka Server</I></B>	 https://kafka.apache.org/quickstart <br>
<I><B> 3. Setup Database Cluster with Message Consumer</B></I><br>
<BR>
	    <B> 3.1. MongoDB Cluster Setup</B>
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
<B> 3.2. VoltDB Cluster Setup</B><br>
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
