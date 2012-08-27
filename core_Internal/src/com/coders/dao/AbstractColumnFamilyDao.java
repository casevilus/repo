package com.coders.dao;

import java.nio.ByteBuffer;

import me.prettyprint.cassandra.serializers.ByteBufferSerializer;
import me.prettyprint.cassandra.serializers.SerializerTypeInferer;
import me.prettyprint.cassandra.serializers.StringSerializer;
import me.prettyprint.hector.api.Keyspace;
import me.prettyprint.hector.api.beans.ColumnSlice;
import me.prettyprint.hector.api.beans.HColumn;
import me.prettyprint.hector.api.factory.HFactory;
import me.prettyprint.hector.api.mutation.Mutator;
import me.prettyprint.hector.api.query.QueryResult;
import me.prettyprint.hector.api.query.SliceQuery;

public abstract class AbstractColumnFamilyDao<KeyType, T> {
	private final Class<T> persistentClass;
	private final Class<KeyType> keyTypeClass;
	protected final Keyspace keySpace;
	private static final String DEFAULT_KEYSPACE_NAME ="";

	protected final String columnFamilyName;

	private final String[] allColumnNames;

	public AbstractColumnFamilyDao(Keyspace keyspace, Class<KeyType> keyTypeClass, Class<T> persistentClass, String columnFamilyName) {
		HFactory.createKeyspace("DEMO", null);
		this.keySpace = keyspace;
		this.keyTypeClass = keyTypeClass;
		this.persistentClass = persistentClass;
		this.columnFamilyName = columnFamilyName;
		this.allColumnNames = DaoHelper.getAllColumnNames(persistentClass);
	}

	public void save(KeyType key, T model) {

		Mutator<Object> mutator = HFactory.createMutator(keySpace, SerializerTypeInferer.getSerializer(keyTypeClass));
		for (HColumn<?, ?> column : DaoHelper.getColumns(model)) {
			mutator.addInsertion(key, columnFamilyName, column);
		}

		mutator.execute();
	}

	public T find(KeyType key) {
		SliceQuery<Object, String, ByteBuffer> query = HFactory.createSliceQuery(keySpace, SerializerTypeInferer.getSerializer(keyTypeClass), StringSerializer.get(), ByteBufferSerializer.get());

		QueryResult<ColumnSlice<String, ByteBuffer>> result = query.setColumnFamily(columnFamilyName).setKey(key).setColumnNames(allColumnNames).execute();

		if (result.get().getColumns().size() == 0) {
			return null;
		}

		try {
			T t = persistentClass.newInstance();
			DaoHelper.populateEntity(t, result);
			return t;
		} catch (Exception e) {
			throw new RuntimeException("Error creating persistent class", e);
		}
	}

	public void delete(KeyType key) {
		Mutator<Object> mutator = HFactory.createMutator(keySpace, SerializerTypeInferer.getSerializer(keyTypeClass));
		mutator.delete(key, columnFamilyName, null, SerializerTypeInferer.getSerializer(keyTypeClass));
	}
}
