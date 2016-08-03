/**
 * 
 */
package org.mediaocean.rest.retail_checkout_counter.service;

import java.util.List;

import org.mediaocean.rest.retail_checkout_counter.model.Customer;

/**
 * @author mangesh
 * Represents CustomerService interface
 */
public interface ICustomerService {

	public Customer addCustomer(Customer customer);
	public Customer getCustomer(int customerId);
	public List<Customer> getAllCustomers();
}
