/**
 * 
 */
package org.mediaocean.rest.retail_checkout_counter.model;

import java.util.List;

/**
 * @author mangesh
 * Represents Customer in retail shop
 */
public class Customer {

	private int id;
	private List<Product> productList;

	public Customer(){
		super();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Product> getProductList() {
		return productList;
	}
	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
	
}
