package com.nesu.ConnectionPool;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class PoolManager {
	public Pool pool;
	public static int maximum, minimum, growth;
	static Properties prop;
	private static Object lock;
	
	public PoolManager(Object lock) {
		pool = Pool.getInstance(lock);
		PoolManager.lock = lock;
	}
	
	public static void loadDrivers() {
		try
		{
			Class.forName("org.postgresql.Driver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("You must have JDBC Driver installed in the JVM.");
		}
	}

	public Connection getConn(Client user) {
		Connection nextConn = null;
		synchronized(lock) {
			System.out.println(this.pool.list.empty());
			if(this.pool.list.empty()) {
				createConn(lock);
			}
			if(this.pool.list.empty()) {
				Client.queue.add(user);
				try {
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		nextConn = this.pool.list.pop();
		System.out.println(this.pool.activeConns);
		return nextConn;
	}
	
	private void createConn(Object lock) {
		synchronized(lock) {
			if(this.pool.activeConns<=maximum-growth) {
				for( int i = 0; i < growth; i++ ) {
					try {
						Connection conn = DriverManager.getConnection("jdbc:postgresql:"+prop.getProperty("database")
						, prop.getProperty("dbuser"), prop.getProperty("dbpassword"));
						this.pool.list.push(conn);
						this.pool.activeConns++;
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				System.out.println(this.pool.activeConns+ " "+ this.pool.list.size());
			}
		}
	}
	
	public static void loadProperties() {
		prop = new Properties();
		InputStream input = null;
		try {
			input = new FileInputStream("config.properties");
			prop.load(input);
			maximum = Integer.valueOf(prop.getProperty("maximum"));
			minimum = Integer.valueOf(prop.getProperty("minimum"));
			growth = Integer.valueOf(prop.getProperty("growth"));
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(input != null) {
				try {
					input.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
