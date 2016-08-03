package org.mediaocean.rest.retail_checkout_counter.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.mediaocean.rest.retail_checkout_counter.dao.impl.InMemoryDaoImpl;
import org.mediaocean.rest.retail_checkout_counter.model.CategoryType;
import org.mediaocean.rest.retail_checkout_counter.model.Customer;
import org.mediaocean.rest.retail_checkout_counter.model.CustomerBill;
import org.mediaocean.rest.retail_checkout_counter.model.Product;
import org.mediaocean.rest.retail_checkout_counter.service.ICustomerService;
import org.springframework.stereotype.Component;

/**
 * @author mangesh Represents CustomerService Implementation
 */
@Component
public class CustomerServiceImpl implements ICustomerService {

	private Map<Integer, Customer> customerMap = InMemoryDaoImpl
			.getCustomerData();

	public CustomerServiceImpl() {
		createCustomerData();
	}
	
	@PostConstruct
	public void post() {
		System.out.println("Inside CustomerServiceImpl() Post...");
	}

	@Override
	public Customer addCustomer(Customer customer) {
		customer.setId(customerMap.size() + 1);
		customerMap.put(customer.getId(), customer);
		return customerMap.get(customer.getId());
	}

	@Override
	public Customer getCustomer(int customerId) {
		return customerMap.get(customerId);
	}

	@Override
	public List<Customer> getAllCustomers() {
		System.out.println("inside getAllCustomers()");
		return new ArrayList<Customer>(customerMap.values());
	}

	private void createCustomerData() {
		List<Product> productList = new ArrayList<Product>();
		Product product1 = new Product();
		product1.setId(1);
		product1.setCost(50);
		product1.setQuantity(3);
		product1.setCategory(CategoryType.A);
		Product product2 = new Product();
		product2.setId(2);
		product2.setCost(25);
		product2.setQuantity(5);
		product2.setCategory(CategoryType.B);
		productList.add(product1);
		productList.add(product2);
		Customer customer1 = new Customer();
		customer1.setId(1);
		customer1.setProductList(productList);

		List<Product> productList1 = new ArrayList<Product>();
		Product product3 = new Product();
		product3.setId(1);
		product3.setCost(55);
		product3.setQuantity(4);
		product3.setCategory(CategoryType.A);
		Product product4 = new Product();
		product4.setId(2);
		product4.setCost(33);
		product4.setQuantity(8);
		product4.setCategory(CategoryType.B);
		productList1.add(product3);
		productList1.add(product4);
		Customer customer2 = new Customer();
		customer2.setId(2);
		customer2.setProductList(productList1);

		customerMap.put(1, customer1);
		customerMap.put(2, customer2);
	}
}
