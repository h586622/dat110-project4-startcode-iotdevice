package no.hvl.dat110.aciotdevice.client;

import java.io.IOException;

import com.google.gson.Gson;

import no.hvl.dat110.aciotdevice.client.AccessCode;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RestClient {

	public RestClient() {
		// TODO Auto-generated constructor stub
		
	}

	private static String logpath = "/accessdevice/log";
	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

	public void doPostAccessEntry(String message) {

		// TODO: implement a HTTP POST on the service to post the message
		
		Gson gson = new Gson();
		
		String melding = gson.toJson(message);
		
		RequestBody body = RequestBody.create(JSON, melding);
		
		OkHttpClient client = new OkHttpClient();
		
		Request request = new Request.Builder()
				.url("http://localhost:8080" + logpath)
				.post(body)
				.build();
		
		try (Response response = client.newCall(request).execute()) {
			System.out.println(response.body().string());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private static String codepath = "/accessdevice/code";
	
	public AccessCode doGetAccessCode() {
		
		OkHttpClient client = new OkHttpClient();
		
		Gson gson = new Gson();
		
		AccessCode kode = null;
		
		Request request = new Request.Builder()
				.url("http://localhost:8080" + codepath)
				.get()
				.build();
		
		Response response;
		try {
			response = client.newCall(request).execute();
			String responseBody = response.body().string();
			kode = gson.fromJson(responseBody, AccessCode.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return kode;
	}
}
