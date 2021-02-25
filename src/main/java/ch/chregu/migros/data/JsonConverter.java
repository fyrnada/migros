package ch.chregu.migros.data;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import ch.chregu.migros.data.datatypes.Club;

public class JsonConverter {

	public List<Club> getClublistFromJson(JSONObject json) {

		List<Club> clubliste = new ArrayList<>();
		json.getJSONArray("results").forEach(clubObject -> {
			JSONObject club = ((JSONObject) clubObject).getJSONObject("organisation");
			String clubName = club.get("name").toString();
			int vouchers = Integer.parseInt(club.get("totalVoucherCount").toString());
			clubliste.add(new Club(clubName, vouchers));
		});
		return clubliste;
	}

}
