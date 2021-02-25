package ch.chregu.migros.datatypes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
class ClubTest {

	private Club club;
	private int vouchers = 12;
	private String clubName = "testClub";

	@BeforeEach
	void initTest() {
		club = new Club(clubName, vouchers);
	}

	@Test
	void testGetName() {
		assertEquals(clubName, club.getName());
	}

	@Test
	void testGetVouchers() {
		assertEquals(vouchers, club.getVouchers());
	}

}
