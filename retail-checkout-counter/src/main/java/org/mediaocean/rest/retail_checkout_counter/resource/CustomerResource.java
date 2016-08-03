package org.mediaocean.rest.retail_checkout_counter.resource;

import java.util.List;

import org.mediaocean.rest.retail_checkout_counter.model.Customer;
import org.mediaocean.rest.retail_checkout_counter.service.ICustomerService;
import org.mediaocean.rest.retail_checkout_counter.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mangesh Represents a CustomerResource
 */

@RestController
@RequestMapping(value = Constants.URI_CUSTOMERS)
public class CustomerResource {

	@Autowired
	private ICustomerService customerService;

	public CustomerResource() {
		super();
	}

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
		
		Customer customerReturn = customerService.addCustomer(customer);
		if (null == customerReturn) {
			return new ResponseEntity<Customer>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Customer>(customerReturn, HttpStatus.OK);
	}

	@RequestMapping(value = Constants.URI_CUSTOMER_ID, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customer> getCustomer(@PathVariable int customerId) {
		Customer customer = customerService.getCustomer(customerId);
		if (null == customer) {
			return new ResponseEntity<Customer>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<List<Customer>> getAllCustomers() {

		List<Customer> customers = customerService.getAllCustomers();
		if (customers.isEmpty()) {
			return new ResponseEntity<List<Customer>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
	}

	/*
	 * @RequestMapping(value="/{customerId}/products",method=RequestMethod.GET,
	 * produces="application/json") public ProductResource getProductResource(){
	 * return new ProductResource(); }
	 */

}
