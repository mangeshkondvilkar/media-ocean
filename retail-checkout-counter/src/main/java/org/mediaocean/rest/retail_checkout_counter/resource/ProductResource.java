package org.mediaocean.rest.retail_checkout_counter.resource;

import java.util.List;

import org.apache.log4j.Logger;
import org.mediaocean.rest.retail_checkout_counter.exceptions.GenericExceptionMapper;
import org.mediaocean.rest.retail_checkout_counter.exceptions.ResourceNotFoundExceptionMapper;
import org.mediaocean.rest.retail_checkout_counter.model.CustomerBill;
import org.mediaocean.rest.retail_checkout_counter.model.ErrorMessage;
import org.mediaocean.rest.retail_checkout_counter.model.Product;
import org.mediaocean.rest.retail_checkout_counter.service.IProductService;
import org.mediaocean.rest.retail_checkout_counter.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mangesh
 *
 */
@RestController
@RequestMapping(value = Constants.URI_CUSTOMER_PRODUCTS)
public class ProductResource {

	@Autowired
	private IProductService productService;

	private static final Logger LOGGER = Logger
			.getLogger(CustomerResource.class);

	public ProductResource() {
		super();
	}

	@RequestMapping(method = RequestMethod.POST, produces = Constants.MEDIA_TYPE_JSON)
	public @ResponseBody ResponseEntity<Product> addProduct(
			@PathVariable("customerId") int customerId,
			@RequestBody Product product) {

		Product productReturn = productService.addProduct(customerId, product);
		if (null == productReturn) {
			return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Product>(productReturn, HttpStatus.CREATED);
	}

	@RequestMapping(value = Constants.URI_CUSTOMER_ADD_PRODUCT_LIST, method = RequestMethod.POST, produces = Constants.MEDIA_TYPE_JSON)
	public @ResponseBody ResponseEntity<List<Product>> addProductList(
			@PathVariable("customerId") int customerId,
			@RequestBody List<Product> productList) {

		List<Product> products = productService.addProductList(customerId,
				productList);

		if (products.isEmpty()) {
			return new ResponseEntity<List<Product>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Product>>(products, HttpStatus.CREATED);
	}

	@RequestMapping(value = Constants.URI_CUSTOMER_PRODUCT_ID, method = RequestMethod.GET, produces = Constants.MEDIA_TYPE_JSON)
	public @ResponseBody ResponseEntity<Product> getProductById(
			@PathVariable("customerId") int customerId,
			@PathVariable("productId") int productId)
			throws ResourceNotFoundExceptionMapper, GenericExceptionMapper {

		Product product = productService.getProductById(customerId, productId);
		if (null == product) {
			throw new ResourceNotFoundExceptionMapper("Product: " + productId
					+ " for customer: " + customerId
					+ " is not found in the system");
		}
		return new ResponseEntity<Product>(product, HttpStatus.FOUND);
	}

	@RequestMapping(method = RequestMethod.GET, produces = Constants.MEDIA_TYPE_JSON)
	public @ResponseBody ResponseEntity<List<Product>> getAllProducts(
			@PathVariable("customerId") int customerId)
			throws ResourceNotFoundExceptionMapper, GenericExceptionMapper {
		
		List<Product> products = productService.getAllProducts(customerId);
		if (products.isEmpty()) {
			throw new ResourceNotFoundExceptionMapper(
					"No products found for customer: " + customerId
							+ " in the system");
		}

		return new ResponseEntity<List<Product>>(products, HttpStatus.FOUND);
	}

	@RequestMapping(value = Constants.URI_CUSTOMER_PRODUCTS_BILL, method = RequestMethod.GET, produces = Constants.MEDIA_TYPE_JSON)
	public @ResponseBody ResponseEntity<CustomerBill> calculateAndReturnBill(
			@PathVariable("customerId") int customerId) throws GenericExceptionMapper{

		CustomerBill bill = productService.calculateAndReturnBill(customerId);
		if (null == bill) {
			throw new GenericExceptionMapper("Error generating bill for customer: "+customerId);
		}
		return new ResponseEntity<CustomerBill>(bill, HttpStatus.OK);
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
