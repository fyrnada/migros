package ch.chregu.migros.data.datatypes;

import java.util.ArrayList;

public class ClubList extends ArrayList<Club> {

	private static final long serialVersionUID = 4788403300613967919L;

	private double totalVouchers;

	private double getTotalVouchers() {
		if (totalVouchers == 0) {

			totalVouchers = this.stream().mapToDouble(Club::getVouchers).sum();
		}
		return totalVouchers;
	}

	public double getMoneyPerVoucher() {
		return 1000000 / getTotalVouchers();

	}

}
