package ch.chregu.migros.datatypes;

public class Club {

	private String name;
	private String category;
	private int vouchers;

	public Club(String clubName, String category, int vouchers) {
		this.name = clubName;
		this.category = category;
		this.vouchers = vouchers;
	}

	public int getVouchers() {
		return vouchers;
	}

	public String getName() {
		return name;
	}

	public String getCategory() {
		return category;
	}

	@Override
	public String toString() {
		return getName() + "\tKategorie " + getCategory() + "\tBons " + getVouchers();
	}

}
