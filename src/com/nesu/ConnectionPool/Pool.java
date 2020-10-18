package com.nesu.ConnectionPool;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Stack;

public class Pool {
	public Stack<Connection> list = new Stack<Connection>();
	private static Pool instance;
	public int activeConns = 0;
	
	private Pool() {
		initialize();
	}
	
	private void initialize() {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream("config.properties"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		System.out.println(PoolManager.minimum);
		for(int i = 0; i<PoolManager.minimum; i++) {
			try {
				Connection conn = DriverManager.getConnection("jdbc:postgresql:"+prop.getProperty("database")
				, prop.getProperty("dbuser"), prop.getProperty("dbpassword"));
				System.out.println(list.size());
				list.push(conn);
				activeConns++;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static Pool getInstance(Object lock) {
		synchronized(lock) {
			if(Pool.instance == null) {
				Pool.instance = new Pool();
			}
			return Pool.instance;
		}
	}
	
	
}
