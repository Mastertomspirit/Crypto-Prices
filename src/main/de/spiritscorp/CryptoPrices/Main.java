package de.spiritscorp.CryptoPrices;

/*
 * @author Tom Spirit
 * @date 31.12.2021
 * @version	1.0.0.0
 */

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

import org.json.JSONArray;

public class Main {

	public static void main(String[] args) {

		start(args);
	}

	static void start(String[] args) {
		
		JSONArray ja;
		ServerRequest request = new ServerRequest(args);
		CryptoWriter writer = new CryptoWriter();
		
		while(true) {
			
			System.out.println("Starte Preisanfrage");
			ja = request.getRequest();
			writer.writeJson(ja);
			System.out.println("Request erfolgreich gespeichert" + System.lineSeparator() + " -------------------------");
			ja.clear();
			
//			alle 30 sekunden 
			
			try {
				Thread.sleep(30000);
			} catch (InterruptedException e) {e.printStackTrace();}
		}		
	}	
}
