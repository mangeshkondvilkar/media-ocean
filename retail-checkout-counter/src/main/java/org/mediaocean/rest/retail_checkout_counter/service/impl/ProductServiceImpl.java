package org.mediaocean.rest.retail_checkout_counter.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.mediaocean.rest.retail_checkout_counter.dao.impl.InMemoryDaoImpl;
import org.mediaocean.rest.retail_checkout_counter.model.Customer;
import org.mediaocean.rest.retail_checkout_counter.model.CustomerBill;
import org.mediaocean.rest.retail_checkout_counter.model.Product;
import org.mediaocean.rest.retail_checkout_counter.service.IProductService;
import org.springframework.stereotype.Component;

/**
 * @author mangesh
 *
 */
@Component
public class ProductServiceImpl implements IProductService {

	private Map<Integer, Customer> customerMap = InMemoryDaoImpl
			.getCustomerData();

	@Override
	public Product addProduct(int customerId, Product product) {

		if (!customerMap.isEmpty()) {
			Set<Entry<Integer, Customer>> entrySet = customerMap.entrySet();
			Iterator<Entry<Integer, Customer>> iterator = entrySet.iterator();

			while (iterator.hasNext()) {
				Map.Entry<Integer, Customer> entry = (Map.Entry<Integer, Customer>) iterator
						.next();
				if (entry.getKey() == customerId) {
					Customer customer = entry.getValue();
					List<Product> productList = customer.getProductList();
					product.setId(productList.size() + 1);
					productList.add(product);
					System.out.println("productList> " + productList);
					customer.setProductList(productList);
					customerMap.put(customerId, customer);

					return product;
				}
			}
		}

		return null;
	}

	@Override
	public List<Product> addProductList(int customerId,
			List<Product> productList) {
		
		return null;
	}

	@Override
	public Product getProductById(int customerId, int productId) {

		if (!customerMap.isEmpty()) {
			Set<Entry<Integer, Customer>> entrySet = customerMap.entrySet();
			Iterator<Entry<Integer, Customer>> iterator = entrySet.iterator();

			while (iterator.hasNext()) {
				Map.Entry<Integer, Customer> entry = (Map.Entry<Integer, Customer>) iterator
						.next();
				if (entry.getKey() == customerId) {
					Customer customer = entry.getValue();
					List<Product> productList = customer.getProductList();

					if (!productList.isEmpty()) {
						for (Product product : productList) {
							if (product.getId() == productId) {
								return product;
							}
						}
					}
				}
			}
		}

		return null;
	}

	@Override
	public List<Product> getAllProducts(int customerId) {

		if (!customerMap.isEmpty()) {
			Set<Entry<Integer, Customer>> entrySet = customerMap.entrySet();
			Iterator<Entry<Integer, Customer>> iterator = entrySet.iterator();

			while (iterator.hasNext()) {
				Map.Entry<Integer, Customer> entry = (Map.Entry<Integer, Customer>) iterator
						.next();
				if (entry.getKey() == customerId) {
					return new ArrayList<Product>(entry.getValue()
							.getProductList());
					// add null checks
				}
			}
		}
		return null;
	}

	@Override
	public CustomerBill calculateAndReturnBill(int customerId) {

		if (null != customerMap.get(customerId)) {
			CustomerBill bill = new CustomerBill();
			bill.setId(customerId + 10);
			bill.setCustomerId(customerId);

			double totalCostOfProducts = 0.0f;
			double totalSalesTax = 0.0f;

			List<Product> produList = customerMap.get(customerId)
					.getProductList();
			Map<Integer, Double> itemizedBill = new HashMap<Integer, Double>();

			for (Product product : produList) {
				double totalCostPerProduct = (double) product.getQuantity()
						* product.getCost();
				System.out.println("totalCostPerProduct: "
						+ totalCostPerProduct);

				double salesTaxPerProduct = (double) totalCostPerProduct
						* (product.getCategory().getValue() / 100.0);
				System.out.println("salesTaxPerProduct: " + salesTaxPerProduct);

				totalCostPerProduct = totalCostPerProduct + salesTaxPerProduct;
				System.out.println("totalCostPerProduct with tax: "
						+ totalCostPerProduct);

				itemizedBill.put(product.getId(), totalCostPerProduct);
				totalCostOfProducts += totalCostPerProduct;
				totalSalesTax += salesTaxPerProduct;
			}

			bill.setItemizedBill(itemizedBill);
			bill.setTotalCostOfProducts(totalCostOfProducts);
			bill.setTotalSalesTax(totalSalesTax);
			
			return bill;
		}
		return null;
	}

}
