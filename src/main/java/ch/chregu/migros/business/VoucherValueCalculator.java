package ch.chregu.migros.business;

import java.util.List;

import ch.chregu.migros.datatypes.Category;
import ch.chregu.migros.datatypes.Club;

public class VoucherValueCalculator {

	private double amountSmall, amountMedium, amountBig;

	public void addClubs(List<Club> clublist, Category category) {
		switch (category) {
			case small :
				clublist.stream().mapToInt(Club::getVouchers).forEach(this::addAmountCategroySmall);
				break;
			case medium :
				clublist.stream().mapToInt(Club::getVouchers).forEach(this::addAmountCategroyMedium);
				break;
			case big :
				clublist.stream().mapToInt(Club::getVouchers).forEach(this::addAmountCategroyBig);
				break;
		}

	}

	private void addAmountCategroySmall(double amount) {
		amountSmall += amount;
	}

	private void addAmountCategroyMedium(double amount) {
		amountMedium += amount;
	}

	private void addAmountCategroyBig(double amount) {
		amountBig += amount;
	}

	public double getValueforClub(Club club) {
		double moneyPerVoucher;
		switch (club.getCategory()) {
			case small :
				moneyPerVoucher = 1000000 / amountSmall;
				break;
			case medium :
				moneyPerVoucher = 1000000 / amountMedium;
				break;
			case big :
				moneyPerVoucher = 1000000 / amountBig;
				break;
			default :
				moneyPerVoucher = 0;
		}
		return moneyPerVoucher * club.getVouchers();
	}

}
