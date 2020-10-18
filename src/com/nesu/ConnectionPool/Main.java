package com.nesu.ConnectionPool;

public class Main {
	public static void main(String[] args) {
		new UserInterface();
		PoolManager.loadProperties();
		PoolManager.loadDrivers();
	}
}
