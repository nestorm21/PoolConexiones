package com.nesu.ConnectionPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.Queue;

public class Client implements Runnable{
	private static class Lock{}
	public static Queue<Client> queue = new LinkedList<>();
	private Connection conn;
	private PoolManager pm;
	private static Lock lock = new Lock();
	Thread t;
	
	public Client() {
		t = new Thread(this);
		t.start();
	}
	
	private void makeRequest() {
		String query = "SELECT * FROM document;";
		Statement stm = null;
		if(conn != null) {
			try {
				stm = conn.createStatement();
				ResultSet rs = stm.executeQuery(query);
				while(rs.next()) {
					UserInterface.area.append(rs.getString(2)+"\n");
				}
				pm.pool.list.push(conn);
				try {
					Client next = Client.queue.peek();
					if(next != null) { Client.queue.remove();lock.notify(); }
				}catch(IllegalMonitorStateException e) {}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Client leaving...");
	}

	@Override
	public void run() {
		pm = new PoolManager(lock);
		conn = pm.getConn(this);
		makeRequest();
	}
}
