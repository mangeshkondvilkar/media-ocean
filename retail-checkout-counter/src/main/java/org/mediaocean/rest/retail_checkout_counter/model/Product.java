/**
 * 
 */
package org.mediaocean.rest.retail_checkout_counter.model;


/**
 * @author mangesh
 * Represents a Product
 */
public class Product {

	private int id;
	private double cost;
	private int quantity;
	private CategoryType category;
	
	public Product(){
		super();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public CategoryType getCategory() {
		return category;
	}
	public void setCategory(CategoryType category) {
		this.category = category;
	}
	
}
