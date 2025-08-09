package com.javaweb.utils;

import java.util.List;

public class StringUtil {
	public static Boolean checkData(String data) {
		if (data != null && !data.equals(""))
			return true;
		else
			return false;
	}

	public static Boolean checkDataList(List<String> data) {
		if (data != null && !data.equals(""))
			return true;
		else
			return false;
	}
}
