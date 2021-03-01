package ch.chregu.migros;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import ch.chregu.migros.business.VoucherValueCalculator;
import ch.chregu.migros.data.JsonConverter;
import ch.chregu.migros.data.JsonReader;
import ch.chregu.migros.datatypes.Club;

public class MigrosAnalyzer {

	private static String leaderboardURL = "https://supportyoursport.migros.ch/api/v1/frontend/leaderboard/?group=%s&limit=%s";
	private static String organisationURL = "https://supportyoursport.migros.ch/api/v1/frontend/organisationfinder/?query=%s";

	public static void main(String[] args) throws IOException, JSONException {

		VoucherValueCalculator vvc = initializeVVC();

		List<String> clubsOfInterest = getClubsOfInterest();

		List<Club> clubsFromQuery = new ArrayList<>();

		clubsOfInterest.forEach(c -> {
			JSONObject orgJson = JsonReader.readJSONFromURL(String.format(organisationURL, c));
			clubsFromQuery.addAll(JsonConverter.getClubsFromQuery(orgJson));
		});

		clubsFromQuery.forEach(c -> {
			System.out.println(c.toString() + "\t" + vvc.getValueforClub(c));
		});
	}

	private static List<String> getClubsOfInterest() {
		List<String> clubList = new ArrayList<>();

		try (BufferedReader br = Files.newBufferedReader(Paths.get("Clubs"))) {
			br.lines().forEach(c -> {
				clubList.add(c.replaceAll(" ", "%20"));
			});
		} catch (IOException e) {
			e.printStackTrace();
		}

		return clubList;
	}

	private static VoucherValueCalculator initializeVVC() {

		Map<String, String> numberOfTeams = getNumberOfTeams();

		VoucherValueCalculator vvc = new VoucherValueCalculator();

		JSONObject json = JsonReader.readJSONFromURL(String.format(leaderboardURL, "small", numberOfTeams.get("A")));
		vvc.addClubs(JsonConverter.getClublistFromJson(json));

		json = JsonReader.readJSONFromURL(String.format(leaderboardURL, "medium", numberOfTeams.get("B")));
		vvc.addClubs(JsonConverter.getClublistFromJson(json));

		json = JsonReader.readJSONFromURL(String.format(leaderboardURL, "big", numberOfTeams.get("C")));
		vvc.addClubs(JsonConverter.getClublistFromJson(json));

		return vvc;

	}

	private static Map<String, String> getNumberOfTeams() {
		HashMap<String, String> hashMap = new HashMap<>();

		JSONObject json = JsonReader.readJSONFromURL(String.format(leaderboardURL, "small", "0"));
		hashMap.put("A", json.get("totalCount").toString());

		json = JsonReader.readJSONFromURL(String.format(leaderboardURL, "medium", "0"));
		hashMap.put("B", json.get("totalCount").toString());

		json = JsonReader.readJSONFromURL(String.format(leaderboardURL, "big", "0"));
		hashMap.put("C", json.get("totalCount").toString());

		return hashMap;
	}
}
