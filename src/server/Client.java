package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	private Socket client;
	
	public Client() {
		
		try {
			
			this.client = new Socket("", 1337);
			
			OutputStream out = this.client.getOutputStream();
			PrintWriter writer = new PrintWriter(out);
			
			InputStream in = this.client.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			
			writer.println("Hallo");
			writer.flush();
			
			writer.close();
			reader.close();
			
			
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
