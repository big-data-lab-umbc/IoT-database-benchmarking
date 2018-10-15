# IoT-database-benchmarking 
<B> How to use this framework   </B>

This framework is divided into 3 tiers: 
1. Sensor Data Producer
2. Message Streaming Server
3. Streaming Message Consumer/ Database Clusters
<br>
<br>
Following are the steps to setup each tier, the steps shown here were used for our test environment, 
user can configure their environment as per their choice i.e. configure each tier on multiple machines or on a single machine
<br>
<br>
<I><B> 1. Setup Sensor Data Producer</I><br></B>
	            a. Download the code and configure it your server<br>
<I><B> 2. Setup Messaging Server (Apache Kafka), use following link to detailed tutorial on how to configure Kafka Server</I></B><br>
	 https://kafka.apache.org/quickstart 
<I><B> 3. Setup Database Cluster with Message Consumer</B></I><br>
	    <B> 3.1. MongoDB Cluster Setup</B><br>
                a. Download and Install MongoDB 3.XX community version<br>
                  https://docs.mongodb.com/manual/tutorial/install-mongodb-on-ubuntu/<br>
                b. Set hostname on each server you want as part of MongoDB shard including Config server and Mongos Server<br>
                c. Update config file<br>
	<br>
        <B> 3.2. VoltDB Cluster Setup</B><br>
                a. Download VoltDB community version<br>
                b. Extract the VoltDB directory<br>
                c. Change directory to ./voltdb/bin/<br>
                d. Execute ./voltdb init --config=./deployment.xml<br>

Follow above steps for each VoltDB node that you want as part of your cluster.<br>
	<br>
  <br>
<I> Note: You can use the deployment.xml file from our repository, make sure to change Kafka importer detail in it. Also you can change the number of host you need for your cluster.	</I>	
