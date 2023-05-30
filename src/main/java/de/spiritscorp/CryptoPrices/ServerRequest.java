package de.spiritscorp.CryptoPrices;

/*
		Copyright (c) 2021 Tom Spirit
		
		This program is free software; you can redistribute it and/or modify
		it under the terms of the GNU General Public License as published by
		the Free Software Foundation; either version 3 of the License, or
		(at your option) any later version.
		
		This program is distributed in the hope that it will be useful,
		but WITHOUT ANY WARRANTY; without even the implied warranty of
		MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
		GNU General Public License for more details.
		
		You should have received a copy of the GNU General Public License
		along with this program; if not, write to the Free Software Foundation,
		Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
*/


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONObject;

public class ServerRequest {

	private JSONArray jo;
	private String[] args;
	private String[] coinPair = new String[] {
			"ETHEUR", "ADAEUR", "TRXEUR", "GRTEUR", "XLMEUR", "MATICEUR", "EURUSDT", "AMPUSDT", "SANDUSDT", "IOTXUSDT", "KEYUSDT", "FETUSDT", "SUIEUR", "CHZEUR" 	
		};
	
	ServerRequest(String[] args){
		this.args = args;
		jo = new JSONArray();
	}
	
	JSONArray getRequest() {

		jo.clear();
		if(args.length != 0) {
			callServer(args);
		}else {
			callServer(coinPair);
		}
		
		return jo;
	}

	private void callServer(String[] currency) {
		
		for(String curr : currency) {
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://api.binance.com/api/v3/ticker/price?symbol=" + curr))
				.setHeader("content-type", "application/json")
				.build();
				
			client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
				.thenApply(HttpResponse::body)
				.thenAccept( s -> saveEntry(s))
				.join();
		}
	}
	
	private void saveEntry(String str) {
		JSONObject obj = new JSONObject(str);
		jo.put(obj);
	}
}
