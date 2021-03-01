package ch.chregu.migros.business;

import java.util.List;

import ch.chregu.migros.datatypes.Club;

public class VoucherValueCalculator {

	private double amountA, amountB, amountC;

	public void addClubs(List<Club> clublist) {
		clublist.forEach(this::addClub);
	}

	private void addClub(Club club) {
		switch (club.getCategory()) {
			case "A" :
				amountA += club.getVouchers();
				break;
			case "B" :
				amountB += club.getVouchers();
				break;
			case "C" :
				amountC += club.getVouchers();
				break;
		}
	}

	public double getValueforClub(Club club) {
		double moneyPerVoucher;
		switch (club.getCategory()) {
			case "A" :
				moneyPerVoucher = 1000000 / amountA;
				break;
			case "B" :
				moneyPerVoucher = 1000000 / amountB;
				break;
			case "C" :
				moneyPerVoucher = 1000000 / amountC;
				break;
			default :
				moneyPerVoucher = 0;
		}
		return moneyPerVoucher * club.getVouchers();
	}

}
