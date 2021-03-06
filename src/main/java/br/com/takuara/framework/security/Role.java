package br.com.takuara.framework.security;

import br.com.takuara.enumeration.EnumLabel;

public enum Role implements EnumLabel {

	ADMIN("Administrator", 5),
	USER("User", 10);

	private String label;
	private int priotiry;

	Role(String label, int priotiry) {
		this.label = label;
		this.priotiry = priotiry;
	}

	@Override
	public String getLabel() {
		return label;
	}

	@Override
	public String getName() {
		return name();
	}


	public int getPriotiry() {
		return priotiry;
	}
}
