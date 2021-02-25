package ch.chregu.migros;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import ch.chregu.migros.datatypes.Club;

public class JsonReader {

	public static void main(String[] args) throws IOException, JSONException {
		JSONObject json = readJSONFromURL(
				"https://supportyoursport.migros.ch/api/v1/frontend/leaderboard/?group=big&limit=90000");

		List<Club> clubliste = new ArrayList<>();
		json.getJSONArray("results").forEach(clubObject -> {
			JSONObject club = ((JSONObject) clubObject).getJSONObject("organisation");
			String clubName = club.get("name").toString();
			int vouchers = Integer.parseInt(club.get("totalVoucherCount").toString());
			clubliste.add(new Club(clubName, vouchers));
		});
		System.out.println("Total Vouchers: " + clubliste.stream().mapToInt(Club::getVouchers).sum());
		clubliste.stream().filter(c -> c.getName().equals("Sportclub Nebikon")).forEach(System.out::println);
	}

	public static JSONObject readJSONFromURL(String url) throws IOException, JSONException {
		try (InputStream inputStream = new URL(url).openStream()) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
			return new JSONObject(reader.readLine());
		}
	}
}
