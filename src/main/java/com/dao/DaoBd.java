package com.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DaoBd {
	public static Connection cn;
	
	public static void conecter(){
		try {
			if (cn == null){
				Context ctx = new InitialContext();
	
				DataSource dataSource = (DataSource) ctx.lookup("java:/comp/env/jdbc/dbMysql");
				
				 cn = dataSource.getConnection();
	
				System.out.println("Connect BD ok...");
			}

			

			}

			catch (Exception e) {

			System.out.println("Connect BD nok..." + e.getMessage());

			}
		
	}
	
	
	public static Connection getCn() {
		return cn;
	}

	

	public static void closeConnection() {
		try {
			cn.close();
			System.out.println("Connection Closed");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}
