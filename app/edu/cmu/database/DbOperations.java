package edu.cmu.database;

import java.sql.*;

public class DbOperations {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.sap.db.jdbc.Driver";  
	static final String DB_URL = "jdbc:sap://54.235.127.76:30015/SYSTEM";

	//  Database credentials
	static final String USER = "SYSTEM";
	static final String PASS = "Cmusv2012";

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		try{
			//STEP 2: Register JDBC driver
			Class.forName("com.sap.db.jdbc.Driver");

			//STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);

			//STEP 4: Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String schema = "set schema DM_PAL";
			stmt.executeUpdate(schema);
			String sql;
			sql = "SELECT * FROM ABC";
			ResultSet rs = stmt.executeQuery(sql);
			System.out.println("Result");
			//STEP 5: Extract data from result set
			while(rs.next()){
				//Retrieve by column name
				String author = rs.getString("COLUMN_1");
				String paper = rs.getString("COLUMN_7");

				//Display values
				System.out.print("Author 1: " + author);
				System.out.print("=====");
				System.out.println("Paper: " + paper);
			}
			//STEP 6: Clean-up environment
			rs.close();
			stmt.close();
			conn.close();
		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}finally{
			//finally block used to close resources
			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
			}// nothing we can do
			try{
				if(conn!=null)
					conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}//end finally try
		}//end try
		System.out.println("Goodbye!");
	}//end main
}
