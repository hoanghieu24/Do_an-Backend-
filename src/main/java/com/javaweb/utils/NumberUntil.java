package com.javaweb.utils;

public class NumberUntil {
	public static Boolean checkNumber(String value) {
		try {
			Long number = Long.parseLong(value);

		}catch(Exception ex){
			return false;
		}
		return true;
	}
}
