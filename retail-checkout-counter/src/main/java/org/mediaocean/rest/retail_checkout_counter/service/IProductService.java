package org.mediaocean.rest.retail_checkout_counter.service;

import java.util.List;

import org.mediaocean.rest.retail_checkout_counter.model.CustomerBill;
import org.mediaocean.rest.retail_checkout_counter.model.Product;

/**
 * @author mangesh Represents ProductService interface
 */
public interface IProductService {

	public Product addProduct(int customerId, Product product);

	public List<Product> addProductList(int customerId, List<Product> produList);

	public Product getProductById(int customerId, int productId);

	public List<Product> getAllProducts(int customerId);

	public CustomerBill calculateAndReturnBill(int customerId);
}
