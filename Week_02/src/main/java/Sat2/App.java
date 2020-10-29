package Sat2;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author wincher
 * <p> demo <p>
 */
public class App {
	public static void main(String[] args) throws IOException {
		HttpClient client = HttpClients.createDefault();
		HttpGet get = new HttpGet("http://localhost:8801");
		
		HttpResponse response = client.execute(get);
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		String line = reader.readLine();
		while (null != line) {
			System.out.println(line);
			line = reader.readLine();
		}
		reader.close();
		
		get.releaseConnection();
	}
}
