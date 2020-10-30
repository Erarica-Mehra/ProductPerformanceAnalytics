package com.analytics.app.dao;

import java.nio.ByteBuffer;

import com.datastax.driver.core.DataType;
import com.datastax.driver.core.LocalDate;
import com.datastax.driver.core.ProtocolVersion;
import com.datastax.driver.core.TypeCodec;
import com.datastax.driver.core.exceptions.InvalidTypeException;

public class LocalDateCodec extends TypeCodec<LocalDate> {

	protected LocalDateCodec(DataType cqlType, Class<LocalDate> javaClass) {
		super(cqlType, javaClass);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ByteBuffer serialize(LocalDate value, ProtocolVersion protocolVersion) throws InvalidTypeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LocalDate deserialize(ByteBuffer bytes, ProtocolVersion protocolVersion) throws InvalidTypeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LocalDate parse(String value) throws InvalidTypeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String format(LocalDate value) throws InvalidTypeException {
		// TODO Auto-generated method stub
		return null;
	}

}
