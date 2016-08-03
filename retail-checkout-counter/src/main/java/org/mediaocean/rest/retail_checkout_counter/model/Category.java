/**
 * 
 */
package org.mediaocean.rest.retail_checkout_counter.model;

/**
 * @author mangesh
 * Represents Product category
 *
 */
public class Category {

	private int id;
	private String type;
	private int salesTaxPercentage;

	public Category(){
		super();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getSalesTaxPercentage() {
		return salesTaxPercentage;
	}
	public void setSalesTaxPercentage(int salesTaxPercentage) {
		this.salesTaxPercentage = salesTaxPercentage;
	}
	
}
