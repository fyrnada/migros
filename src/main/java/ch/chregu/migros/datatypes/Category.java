package ch.chregu.migros.datatypes;

public enum Category {
	small("A"), medium("B"), big("C");

	public final String label;

	private Category(String label) {
		this.label = label;
	}

	public static Category valueOfLabel(String label) {
		for (Category e : values()) {
			if (e.label.equals(label)) {
				return e;
			}
		}
		return null;
	}
}
