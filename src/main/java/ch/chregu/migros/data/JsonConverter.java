package ch.chregu.migros.data;

import org.json.JSONObject;

import ch.chregu.migros.data.datatypes.Club;
import ch.chregu.migros.data.datatypes.ClubList;

public class JsonConverter {

	public ClubList getClublistFromJson(JSONObject json) {

		ClubList clubList = new ClubList();
		json.getJSONArray("results").forEach(clubObject -> {
			JSONObject club = ((JSONObject) clubObject).getJSONObject("organisation");
			String clubName = club.get("name").toString();
			int vouchers = Integer.parseInt(club.get("totalVoucherCount").toString());
			clubList.add(new Club(clubName, vouchers));
		});
		return clubList;
	}

}
