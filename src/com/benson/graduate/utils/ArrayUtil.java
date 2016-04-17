package com.benson.graduate.utils;

import org.apache.commons.lang.ArrayUtils;

public class ArrayUtil {
	
	public ArrayUtil() {}

	public static boolean isNotEmpty(Object array[]) {
		return !ArrayUtils.isEmpty(array);
	}

	public static boolean isEmpty(Object array[]) {
		return ArrayUtils.isEmpty(array);
	}

	public static Object[] concat(Object array1[], Object array2[]) {
		return ArrayUtils.addAll(array1, array2);
	}

	public static boolean contains(Object array[], Object obj) {
		return ArrayUtils.contains(array, obj);
	}
}
