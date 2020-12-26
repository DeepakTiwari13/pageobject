package com.generic.project.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;
import org.apache.log4j.Logger;

public class OracleDB {
	
    static Logger logger = Logger.getLogger(OracleDB.class); 

	public Connection makeConnectionWithDB() {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			String dbURL = "jdbc:oracle:thin:tiger/scott@localhost:1521:productDB";
			conn = DriverManager.getConnection(dbURL);
			if(conn!= null) {
				logger.info("Database connection established ");
			}
		} catch (Exception e) {
			logger.info("Database connection failed ");
			e.printStackTrace();
		}
		return conn;
	}

	public HashMap<Integer,HashMap<String,String>> getDBResultUsingSelectQuery(Connection conn,Statement stmt,ResultSet rs,String query) {
		HashMap<Integer,HashMap<String,String>> rsetData = null;
		HashMap<String,String> rowData = null ; 
		try {
			rowData = new HashMap<String,String>();
			rsetData = new HashMap<Integer,HashMap<String,String>>();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			int i = 0;
			   while(rs.next()) {
			         String valueOne  = rs.getString("");
			         String valueTwo = rs.getString("");
			         i++;
			         rowData.put("key1", valueOne);
			         rowData.put("key2", valueTwo);
			         rsetData.put(i,rowData);
			   }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		logger.debug("Select query output "+rsetData);
		return rsetData;
	}
	
	public HashMap<Integer,HashMap<String,String>> getDBResultUsingPreparedStatement(Connection conn,PreparedStatement pStmt,
			ResultSet rs,String query,String val1,String val2) {
		HashMap<Integer,HashMap<String,String>> rsetData = null;
		HashMap<String,String> rowData = null ; 
		try {
			rowData = new HashMap<String,String>();
			rsetData = new HashMap<Integer,HashMap<String,String>>();
			pStmt = conn.prepareStatement(query);
			pStmt.setString(1, val1);
			pStmt.setString(2, val2);
			int rows = pStmt.executeUpdate();
			logger.debug("Number of rows updated "+rows);
			int i = 0;
			   while(rs.next()) {
			         String valueOne  = rs.getString("");
			         String valueTwo = rs.getString("");
			         i++;
			         rowData.put("key1", valueOne);
			         rowData.put("key2", valueTwo);
			         rsetData.put(i,rowData);
			   }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		logger.debug("Select query output [ prepared statement ]"+rsetData);
		return rsetData;
	}
	
	public HashMap<Integer,LinkedList<String>> getEntireRows(Connection conn,Statement stmt,ResultSet rs,String query) {
		HashMap<Integer,LinkedList<String>> rowData = null;
		LinkedList<String> rows ;
		try {
			rowData = new HashMap<Integer,LinkedList<String>>();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			int i = 0;
			   while(rs.next()) {
			         String valueOne  = rs.getString("");
			         String valueTwo = rs.getString("");
			         String valueThree = rs.getString("");
			         rows = addDataToList(valueOne,valueTwo,valueThree);
			         i++;
			         rowData.put(i,rows);
			   }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		logger.debug("Entire row data "+rowData);
		return rowData;
	}
	
	public void closeDbConnection(Connection conn, Statement stmt, ResultSet rs,PreparedStatement pStmt) {
		if (conn != null) {
			try {
				logger.info("Closing DB connection ");
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
			if (stmt != null) {
				try {
					logger.info("Closing statement object ");
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
				if (rs != null) {
					try {
						logger.info("Closing resultset object ");
						rs.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
		if (pStmt != null) {
			try {
				logger.info("Closing Prepared statement object ");
				pStmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	LinkedList<String> addDataToList(String columnOne, String columnTwo, String columnThree) {
		LinkedList<String> l = new LinkedList<String>();
		l.add(columnOne);
		l.add(columnTwo);
		l.add(columnThree);
		logger.debug("Added following elements to list "+columnOne+" "+columnTwo+" "+columnThree);
		return l;
	}
}
