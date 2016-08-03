package org.mediaocean.rest.retail_checkout_counter.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.mediaocean.rest.retail_checkout_counter.model.Customer;

/**
 * @author Mangesh
 *
 */
public class InMemoryDaoImpl {

	private static final Map<Integer, Customer> customerData = new HashMap<Integer, Customer>();

	public static Map<Integer, Customer> getCustomerData() {
		return customerData;
	}

}
