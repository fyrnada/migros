package ch.chregu.migros.datatypes;

public class Club {

	private String name;
	private Category category;
	private int vouchers;

	public Club(String clubName, Category category, int vouchers) {
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

	public Category getCategory() {
		return category;
	}

	@Override
	public String toString() {
		return getName() + "\tKategorie " + getCategory().label + "\tBons " + getVouchers();
	}

}
