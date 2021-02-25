package ch.chregu.migros;

import java.io.IOException;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import ch.chregu.migros.data.JsonConverter;
import ch.chregu.migros.data.JsonReader;
import ch.chregu.migros.data.datatypes.Club;

public class MigrosAnalyzer {

	public static void main(String[] args) throws IOException, JSONException {

		String initalURL = "https://supportyoursport.migros.ch/api/v1/frontend/leaderboard/?group=big&limit=90000";

		JSONObject json = JsonReader.readJSONFromURL(initalURL);

		List<Club> clubliste = new JsonConverter().getClublistFromJson(json);

		System.out.println("Total Vouchers: " + clubliste.stream().mapToInt(Club::getVouchers).sum());

		clubliste.stream().filter(c -> c.getName().equals("Sportclub Nebikon")).forEach(System.out::println);
	}
}
