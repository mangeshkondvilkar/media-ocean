/**
 * 
 */
package org.mediaocean.rest.retail_checkout_counter.model;

import java.util.Map;

/**
 * @author mangesh
 * Represents a particular customer bill
 */
public class CustomerBill {

	private int id;
	private int customerId;
	private Map<Integer, Double> itemizedBill;
	private double totalCostOfProducts;
	private double totalSalesTax;

	public CustomerBill(){
		super();	
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public Map<Integer, Double> getItemizedBill() {
		return itemizedBill;
	}
	public void setItemizedBill(Map<Integer, Double> itemizedBill) {
		this.itemizedBill = itemizedBill;
	}
	public double getTotalCostOfProducts() {
		return totalCostOfProducts;
	}
	public void setTotalCostOfProducts(double totalCostOfProducts) {
		this.totalCostOfProducts = totalCostOfProducts;
	}
	public double getTotalSalesTax() {
		return totalSalesTax;
	}
	public void setTotalSalesTax(double totalSalesTax) {
		this.totalSalesTax = totalSalesTax;
	}
	
}
