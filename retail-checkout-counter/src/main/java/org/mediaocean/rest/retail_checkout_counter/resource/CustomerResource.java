package org.mediaocean.rest.retail_checkout_counter.resource;

import java.util.List;

import org.apache.log4j.Logger;
import org.mediaocean.rest.retail_checkout_counter.exceptions.GenericExceptionMapper;
import org.mediaocean.rest.retail_checkout_counter.exceptions.ResourceNotFoundExceptionMapper;
import org.mediaocean.rest.retail_checkout_counter.model.Customer;
import org.mediaocean.rest.retail_checkout_counter.model.ErrorMessage;
import org.mediaocean.rest.retail_checkout_counter.service.ICustomerService;
import org.mediaocean.rest.retail_checkout_counter.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
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

	private static final Logger LOGGER = Logger
			.getLogger(CustomerResource.class);

	public CustomerResource() {
		super();
	}

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Customer> addCustomer(
			@RequestBody Customer customer) {

		Customer customerReturn = customerService.addCustomer(customer);
		if (null == customerReturn) {
			return new ResponseEntity<Customer>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Customer>(customerReturn, HttpStatus.OK);
	}

	@RequestMapping(value = Constants.URI_CUSTOMER_ID, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customer> getCustomer(@PathVariable int customerId)
			throws ResourceNotFoundExceptionMapper, GenericExceptionMapper {

		Customer customer = customerService.getCustomer(customerId);
		if (null == customer) {
			throw new ResourceNotFoundExceptionMapper("Customer with id "
					+ customerId + " is not found in the system");
		}
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<List<Customer>> getAllCustomers()
			throws ResourceNotFoundExceptionMapper, GenericExceptionMapper {

		List<Customer> customers = customerService.getAllCustomers();
		if (customers.isEmpty()) {
			throw new ResourceNotFoundExceptionMapper(
					"No customers found in the system");
		}
		return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
	}

	@ExceptionHandler(ResourceNotFoundExceptionMapper.class)
	public ResponseEntity<ErrorMessage> exceptionHandler(Exception ex) {
		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setErrorCode(HttpStatus.NOT_FOUND.value());
		errorMessage.setErrorMessage(ex.getMessage());
		return new ResponseEntity<ErrorMessage>(errorMessage,
				HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(GenericExceptionMapper.class)
	public ResponseEntity<ErrorMessage> genericExceptionHandler(Exception ex) {
		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorMessage.setErrorMessage(ex.getMessage());
		return new ResponseEntity<ErrorMessage>(errorMessage,
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
