package com.sreekanth.mailGuardian.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

/**
 * Utility class for handling HTTP-related operations.
 */
public class HttpUtils {

	/**
	 * Reads the HTTP response status code from the provided BufferedReader.
	 *
	 * @param in The BufferedReader containing the HTTP response.
	 * @return The HTTP status code or -1 if it couldn't be parsed.
	 * @throws IOException If an I/O error occurs while reading from the input
	 *                     stream.
	 */
	@SuppressWarnings("unused")
	public
	int hear(BufferedReader in) throws IOException {
		String line = null;
		int res = 0;

		// Read lines until a non-continuation line is encountered
		while ((line = in.readLine()) != null) {
			String pfx = line.substring(0, 3);
			try {
				// Parse the first three characters as an integer (HTTP status code)
				res = Integer.parseInt(pfx);
			} catch (Exception ex) {
				// Set the result to -1 if parsing fails
				res = -1;
			}
			if (line.charAt(3) != '-')
				break; // Exit loop if the line is not a continuation line
		}
		return res;
	}

	/**
	 * Writes the provided text to the BufferedWriter and flushes the stream.
	 *
	 * @param wr   The BufferedWriter to write to.
	 * @param text The text to be written.
	 * @throws IOException If an I/O error occurs while writing to the output
	 *                     stream.
	 */
	@SuppressWarnings("unused")
	public
	void say(BufferedWriter wr, String text) throws IOException {
		// Write the text to the output stream and flush the BufferedWriter
		wr.write(text + "\r\n");
		wr.flush();
	}



	public static List<String> readJsonFromUrl(String jsonUrl) throws IOException {
        URL url = new URL(jsonUrl);
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

      
		JsonParser parser = new JsonParser();
		JsonElement rootElement = parser.parse(reader);

        List<String> domains = new ArrayList<>();

        // Assuming the JSON structure is an array of strings
        if (rootElement.isJsonArray()) {
            JsonArray jsonArray = rootElement.getAsJsonArray();
            Iterator<JsonElement> iterator = jsonArray.iterator();
            while (iterator.hasNext()) {
                domains.add(iterator.next().getAsString());
            }
        }

        reader.close();
        return domains;
    }
	
	
	public static List<String> readTextFromUrl(String jsonUrl) throws IOException {
        URL url = new URL(jsonUrl);
        Scanner s = new Scanner(url.openStream());
        ArrayList<String> domains = new ArrayList<String>();
        while (s.hasNext()){
            domains.add(s.next());
        }
        s.close();

        return domains;
    }
}
