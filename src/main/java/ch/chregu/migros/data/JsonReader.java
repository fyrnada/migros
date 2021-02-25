package ch.chregu.migros.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonReader {

	public static JSONObject readJSONFromURL(String url) throws IOException, JSONException {
		try (InputStream inputStream = new URL(url).openStream()) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
			return new JSONObject(reader.readLine());
		}
	}
}
