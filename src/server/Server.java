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
	
	ServerSocket server;
	Socket client;
	
	public Server() {
		
		try {
			
			this.server = new ServerSocket(1337);
			
			this.client = server.accept();
			
			OutputStream out = this.client.getOutputStream();
			PrintWriter writer = new PrintWriter(out);
			
			InputStream in = this.client.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			
			String incomingText = null;
			
			while((incomingText = reader.readLine()) != null) {
				System.out.println(incomingText);
			}
			
			writer.close();
			reader.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
