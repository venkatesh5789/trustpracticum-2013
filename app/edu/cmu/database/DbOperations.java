package edu.cmu.database;

import java.sql.*;

public class DbOperations {

	private static String sqlQuery;
	private Connection connection = null;
	private Statement statement = null;
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost/test";
	//	//  Database credentials
	static final String USER = "Shuai";
	static final String PASS = "123456";

	public DbOperations() throws ClassNotFoundException, SQLException {
		super();
		Class.forName(JDBC_DRIVER);

		System.out.println("Connecting to database...");
		connection = DriverManager.getConnection(DB_URL,"root","");

		System.out.println("Creating statement...");
		statement = connection.createStatement();
		// TODO Auto-generated constructor stub
	}

	public ResultSet callDatabaseQuery(String sqlQuery) throws SQLException {
		String sql;
		sql = sqlQuery;
		ResultSet rs = this.statement.executeQuery(sql);
		return rs;
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		DbOperations blah = new DbOperations();
		ResultSet rs = blah.callDatabaseQuery("SELECT * FROM coauthor.Coauthors");
		while(rs.next()){
			//Retrieve by column name
			String author = rs.getString("Author");
			String coauthor = rs.getString("Coauthor");

			//Display values
			System.out.print("Author 1: " + author);
			System.out.print("=====");
			System.out.println("Coauthor: " + coauthor);
		}
	}
//		Connection conn = null;
//		Statement stmt = null;
//		try{
//			//STEP 2: Register JDBC driver
//			Class.forName(JDBC_DRIVER);
//
//			//STEP 3: Open a connection
//			System.out.println("Connecting to database...");
//			conn = DriverManager.getConnection(DB_URL,"root","");
//
//			//STEP 4: Execute a query
//			System.out.println("Creating statement...");
//			stmt = conn.createStatement();
//			//			String schema = "set schema coauthor";
//			//			stmt.executeUpdate(schema);
//			String sql;
//			sql = "SELECT * FROM coauthor.Coauthors";
//			ResultSet rs = stmt.executeQuery(sql);
//			System.out.println("Result");
//			//STEP 5: Extract data from result set
//			while(rs.next()){
//				//Retrieve by column name
//				String author = rs.getString("Author");
//				String coauthor = rs.getString("Coauthor");
//
//				//Display values
//				System.out.print("Author 1: " + author);
//				System.out.print("=====");
//				System.out.println("Coauthor: " + coauthor);
//			}
//			//STEP 6: Clean-up environment
//			rs.close();
//			stmt.close();
//			conn.close();
//		}catch(SQLException se){
//			//Handle errors for JDBC
//			se.printStackTrace();
//		}catch(Exception e){
//			//Handle errors for Class.forName
//			e.printStackTrace();
//		}finally{
//			//finally block used to close resources
//			try{
//				if(stmt!=null)
//					stmt.close();
//			}catch(SQLException se2){
//			}// nothing we can do
//			try{
//				if(conn!=null)
//					conn.close();
//			}catch(SQLException se){
//				se.printStackTrace();
//			}//end finally try
//		}//end try
//		System.out.println("Goodbye!");
//	}//end main
}
