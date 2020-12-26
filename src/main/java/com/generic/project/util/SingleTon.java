package com.generic.project.util;

import org.apache.log4j.Logger;

public class SingleTon {

    static Logger logger = Logger.getLogger(SingleTon.class); 

	private static SingleTon instance;

	private SingleTon() {
		logger.info("Inside singleton class ");
	}
	public static SingleTon getInstance() {
		if (instance == null) {
			instance = new SingleTon();
		}
		return instance;
	}
}
