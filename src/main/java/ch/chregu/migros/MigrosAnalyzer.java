package ch.chregu.migros;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.json.JSONException;
import org.json.JSONObject;

import ch.chregu.migros.business.VoucherValueCalculator;
import ch.chregu.migros.data.JsonConverter;
import ch.chregu.migros.data.JsonReader;
import ch.chregu.migros.datatypes.Category;
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
			clubList = br.lines().map(c -> c.replaceAll(" ", "%20")).collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return clubList;
	}

	private static VoucherValueCalculator initializeVVC() {

		Map<Category, String> numberOfTeams = getNumberOfTeams();

		VoucherValueCalculator vvc = new VoucherValueCalculator();

		Stream.of(Category.values()).forEach(cat -> {
			JSONObject json = JsonReader
					.readJSONFromURL(String.format(leaderboardURL, cat.name(), numberOfTeams.get(cat)));
			vvc.addClubs(JsonConverter.getClublistFromJson(json), cat);

		});

		return vvc;

	}

	private static Map<Category, String> getNumberOfTeams() {
		HashMap<Category, String> hashMap = new HashMap<>();

		Stream.of(Category.values()).forEach(cat -> {
			JSONObject json = JsonReader.readJSONFromURL(String.format(leaderboardURL, cat.name(), "0"));
			hashMap.put(cat, json.get("totalCount").toString());

		});

		return hashMap;
	}
}
