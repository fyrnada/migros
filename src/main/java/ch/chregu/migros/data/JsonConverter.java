package ch.chregu.migros.data;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import ch.chregu.migros.datatypes.Category;
import ch.chregu.migros.datatypes.Club;

public class JsonConverter {

	public static List<Club> getClublistFromJson(JSONObject json) {
		List<Club> clubList = new ArrayList<>();

		json.getJSONArray("results").forEach(clubObject -> {
			JSONObject clubJson = ((JSONObject) clubObject).getJSONObject("organisation");
			clubList.add(createClubFromJson(clubJson));
		});

		return clubList;
	}

	public static List<Club> getClubsFromQuery(JSONObject json) {
		List<Club> clubList = new ArrayList<>();

		json.getJSONArray("results").forEach(clubObject -> {
			clubList.add(createClubFromJson((JSONObject) clubObject));
		});
		return clubList;
	}

	private static Club createClubFromJson(JSONObject clubJson) {
		String clubName = clubJson.get("name").toString();
		String category = clubJson.get("group").toString();
		int vouchers = Integer.parseInt(clubJson.get("totalVoucherCount").toString());

		return new Club(clubName, Category.valueOfLabel(category), vouchers);
	}

}
