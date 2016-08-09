package org.mediaocean.rest.retail_checkout_counter.model;

public enum CategoryType {

	A(10), B(20), C(0);

	private double value;

	private CategoryType(double value) {
		this.value = value;
	}

	public double getValue() {
		return this.value;
	}
}
