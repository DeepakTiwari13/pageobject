package com.generic.project.util;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

public class FileValidator {
	
	static Logger logger = Logger.getLogger(FileValidator.class);

	public boolean isJason(String output) {
		logger.debug("Checking if response is Json");
		try {
				new JSONObject(output);
		} 		catch (Exception e1) {
				logger.error(e1.fillInStackTrace());
				try {
						new JSONArray(output);
				}		catch (Exception e2) {
						logger.error(e2.fillInStackTrace());
				return false;
			}
		}
		return true;
	}
	
	public JSONObject getJason(String serverResponse) {
		logger.debug("Returning Json response");
		JSONObject obj = new JSONObject(serverResponse);
		return modifyJsonResponse(obj);
		}
	
	 public JSONObject modifyJsonResponse(JSONObject jobject) {
			logger.debug("Inside modify Json response");
		if (jobject.has("mid")) {
			int iD = jobject.getInt("mid");
			jobject.put("mid", String.valueOf(iD));
		}
		return jobject;
	 }
}
