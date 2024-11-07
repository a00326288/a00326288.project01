package com.a00326288.project01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DBA {

	//code from https://github.com/xerial/sqlite-jdbc?tab=readme-ov-file#download
	/*
	public static void dbConnection() {
		
		try
        (
          // create a database connection
          Connection connection = DriverManager.getConnection("jdbc:sqlite:db/a00326288.db");
          Statement statement = connection.createStatement();
        )
        {
          statement.setQueryTimeout(30);  // set timeout to 30 sec.
          System.out.println("Connected.");
          statement.executeUpdate("drop table if exists events");
          statement.executeUpdate("create table events (event_id integer, event_name string)");
          statement.executeUpdate("insert into events values(1, 'leo')");
          statement.executeUpdate("insert into events values(2, 'yui')");
          ResultSet rs = statement.executeQuery("select * from events");
          while(rs.next())
          {
            // read the result set
            System.out.println("name = " + rs.getString("event_name"));
            System.out.println("id = " + rs.getInt("event_id"));
          }
          
          
        }
        catch(SQLException e)
        {
          // if the error message is "out of memory",
          // it probably means no database file is found
          e.printStackTrace(System.err);
        }
		
	}*/

	
	
	
	
	public static List<Map<String, Object>> dbConnection(String SQL) {
		try
        (
       
          // create a database connection
          Connection connection = DriverManager.getConnection("jdbc:sqlite:/Users/jmclaugh/git/a00326288.project01/com.a00326288.project01/db/a00326288.db");
          Statement statement = connection.createStatement();
        )
        {
          statement.setQueryTimeout(30);  // set timeout to 30 sec.
          ResultSet rs = statement.executeQuery(SQL);
          
 
          List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
          ResultSetMetaData metaData = rs.getMetaData();
          int columnCount = metaData.getColumnCount();

          while (rs.next()) {
              Map<String, Object> columns = new LinkedHashMap<String, Object>();

              for (int i = 1; i <= columnCount; i++) {
                  columns.put(metaData.getColumnLabel(i), rs.getObject(i));
              }

              rows.add(columns);
          
          
          }
          return rows;
          
        	  
          }
        catch(SQLException e)
        {
          // if the error message is "out of memory",
          // it probably means no database file is found
          e.printStackTrace(System.err);
        }
		return null;
 
		
		
		
	}
	

	/*
	public static boolean dbConnection(String Username) {
		try
        (
          // create a database connection
        Connection connection = DriverManager.getConnection("jdbc:sqlite:/Users/jmclaugh/git/a00326288.project01/com.a00326288.project01/db/a00326288.db");
		Statement statement = connection.createStatement();
          
        )
        {
			
			statement.setQueryTimeout(30);
	        ResultSet rs = statement.executeQuery("SELECT * FROM uam WHERE username ='"+Username+"';");
	        if(rs.getRowId(1)==null) {
	        	return true;
	        	
	        }else{
	        	
	        	return false;
	        }
	            
	       
        }
        catch(SQLException e)
        {
          // if the error message is "out of memory",
          // it probably means no database file is found
          e.printStackTrace(System.err);
        }
		return false;
				
	}
	
	*/
	
	
	public static String dbConnection(String hashStringUID,String hashPass) {
		
		String username = null;
		try
        (
          // create a database connection
          Connection connection = DriverManager.getConnection("jdbc:sqlite:/Users/jmclaugh/git/a00326288.project01/com.a00326288.project01/db/a00326288.db");
          Statement statement = connection.createStatement();
        )
        {
          statement.setQueryTimeout(30);  // set timeout to 30 sec.
          ResultSet rs = statement.executeQuery("SELECT * FROM uam WHERE UID ='"+hashStringUID+"' and password='"+hashPass+"';");
          
          while(rs.next())
          {
        	username=rs.getString("username");
          }
          
          return username;
          
        }
        catch(SQLException e)
        {
          // if the error message is "out of memory",
          // it probably means no database file is found
          e.printStackTrace(System.err);
        }
		return null;
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
	
	
}
