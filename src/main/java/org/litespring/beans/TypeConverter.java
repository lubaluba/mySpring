package org.litespring.beans;

import org.litespring.exception.TypeMismatchException;

public interface TypeConverter {

	<T> T convertIfNecessary(Object value, Class<T> requiredType) throws TypeMismatchException;
}
