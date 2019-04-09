package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	public static int serverPort = 1337;
	private ServerSocket server;
	private Database database;
	
	public Database getDatabase() { return this.database; }
	
	public Server() {
		this.database = new Database();
	}
	
	public void start() {
		try {
			this.server = new ServerSocket(serverPort);
			System.out.println("Server gestartet");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void waitForClient() {
		
		Socket client;
		
		while(true) {
			
			try {
				client = server.accept();
				OutputStream out = client.getOutputStream();
				PrintWriter writer = new PrintWriter(out);
				
				InputStream in = client.getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(in));
				
				String incomingText = null;
				
				while((incomingText = reader.readLine()) != null) {
					System.out.println(incomingText);
				}
				
				writer.close();
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	public Server getServer() {
		return this;
	}
	
	public boolean testMethodeOfServer(String name) {
		DatenBankManager bdm = new DatenBankManager();
		return bdm.testUserName(name);
	}
	
	public static void main(String[] args) {
		Server server = new Server();
		server.start();
		server.waitForClient();
	}
	
}
