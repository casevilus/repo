package com.coders.cassandra.test;

import java.util.Arrays;
import java.util.Date;

import me.prettyprint.cassandra.serializers.StringSerializer;
import me.prettyprint.cassandra.service.ThriftKsDef;
import me.prettyprint.cassandra.service.template.ColumnFamilyTemplate;
import me.prettyprint.cassandra.service.template.ColumnFamilyUpdater;
import me.prettyprint.cassandra.service.template.ThriftColumnFamilyTemplate;
import me.prettyprint.hector.api.Cluster;
import me.prettyprint.hector.api.Keyspace;
import me.prettyprint.hector.api.ddl.ColumnFamilyDefinition;
import me.prettyprint.hector.api.ddl.ComparatorType;
import me.prettyprint.hector.api.ddl.KeyspaceDefinition;
import me.prettyprint.hector.api.exceptions.HectorException;
import me.prettyprint.hector.api.factory.HFactory;

public class TestDB {

	public static void main(String[] args) {

		Cluster myCluster = HFactory.getOrCreateCluster("test-cluster", "localhost:9160");

		KeyspaceDefinition keyspaceDef = myCluster.describeKeyspace("DEMO");

		Keyspace ksp = HFactory.createKeyspace("DEMO", myCluster);

		ColumnFamilyTemplate<String, String> template = new ThriftColumnFamilyTemplate<String, String>(ksp, "Users", StringSerializer.get(), StringSerializer.get());
		
		ColumnFamilyUpdater<String, String> updater = template.createUpdater("a key");
		updater.setString("domain", "www.Ozkan.com");
		updater.setLong("time", System.currentTimeMillis());
		updater.setDate("aaa", new Date());

		try {
		    template.update(updater);
		} catch (HectorException e) {
		    e.printStackTrace();
		}
	}

}
