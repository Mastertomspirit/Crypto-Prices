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

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.json.JSONArray;

public class CryptoWriter {

	private Path path = Paths.get(System.getProperty("user.home"), "CryptoWriter");
	private Path file = path.resolve("Crypto.json");
	
	public CryptoWriter() {
		
		if(!Files.exists(path)) {
			try {
				Files.createDirectory(path);
			} catch (IOException e) {e.printStackTrace();}
		}
	}
	
	void writeJson(Object str) {
		
		String s = ((JSONArray) str).toString(2);
		s = s.replace(',', '-');
		s = s.replace('.', ',');
		try {
			Files.writeString(file, s, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
		} catch (IOException e) {e.printStackTrace();}
	}
}
