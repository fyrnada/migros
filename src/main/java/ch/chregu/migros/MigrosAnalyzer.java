package ch.chregu.migros;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import ch.chregu.migros.data.JsonConverter;
import ch.chregu.migros.data.JsonReader;
import ch.chregu.migros.data.datatypes.ClubList;

public class MigrosAnalyzer {

	public static void main(String[] args) throws IOException, JSONException {

		String initalURL = "https://supportyoursport.migros.ch/api/v1/frontend/leaderboard/?group=big&limit=10000";

		JSONObject json = JsonReader.readJSONFromURL(initalURL);

		ClubList clubliste = new JsonConverter().getClublistFromJson(json);

		System.out.println("Money per Voucher: " + clubliste.getMoneyPerVoucher());

		clubliste.stream().filter(club -> club.getName().equals("Sportclub Nebikon")).forEach(club -> {
			System.out.print(club);
			System.out.println(" ==> " + clubliste.getMoneyPerVoucher() * club.getVouchers());
		});
	}
}
