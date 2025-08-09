package com.javaweb.utils;

import java.util.Map;

public class MapUntil {
	public static <T> T getObject(Map<String, Object> param, String key , Class<T> tClass) {

		Object value = param.getOrDefault(key, null);
		if (value != null) {
			String trimmedValue = value.toString().trim();

			// Logging để kiểm tra dữ liệu được lấy ra
			System.out.println("Key: " + key + ", Value: " + trimmedValue);

			if (tClass.getTypeName().equals("java.lang.Long")) {
				value = !trimmedValue.isEmpty() ? Long.valueOf(trimmedValue) : null;
				System.out.println("Long value: " + value);
			}
			else if (tClass.getTypeName().equals("java.lang.Integer")) {
				value = !trimmedValue.isEmpty() ? Integer.valueOf(trimmedValue) : null;
				System.out.println("Integer value: " + value);
			}
			else if (tClass.getTypeName().equals("java.lang.String")) {
				value = !trimmedValue.isEmpty() ? trimmedValue : null;
				System.out.println("String value: " + value);
			}

			return tClass.cast(value);
		}
		return null;
	}
}
