package com.a00326288.project01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBA {

	//code from https://github.com/xerial/sqlite-jdbc?tab=readme-ov-file#download
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try
        (
          // create a database connection
          Connection connection = DriverManager.getConnection("jdbc:sqlite:a00326288.db");
          Statement statement = connection.createStatement();
        )
        {
          statement.setQueryTimeout(30);  // set timeout to 30 sec.

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
	}
}
