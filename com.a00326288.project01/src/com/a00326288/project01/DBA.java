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
	
	public static List<Map<String, Object>> dbConnection(String SQL) {
		try
        (
       
          // create a database connection
          Connection connection = DriverManager.getConnection("jdbc:sqlite:db/a00326288.db");
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
	

	
}
