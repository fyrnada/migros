package ch.chregu.migros.datatypes;

public class Club {

	private String name;
	private int vouchers;

	public Club(String clubName, int vouchers) {
		this.name = clubName;
		this.vouchers = vouchers;
	}

	public int getVouchers() {
		return vouchers;
	}

	public String getName() {

		return name;
	}

}
