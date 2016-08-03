package org.mediaocean.rest.retail_checkout_counter.resource;

import java.util.List;

import org.mediaocean.rest.retail_checkout_counter.model.CustomerBill;
import org.mediaocean.rest.retail_checkout_counter.model.Product;
import org.mediaocean.rest.retail_checkout_counter.service.IProductService;
import org.mediaocean.rest.retail_checkout_counter.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
			@PathVariable("productId") int productId) {

		Product product = productService.getProductById(customerId, productId);
		if (null == product) {
			return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Product>(product, HttpStatus.FOUND);
	}

	@RequestMapping(method = RequestMethod.GET, produces = Constants.MEDIA_TYPE_JSON)
	public @ResponseBody ResponseEntity<List<Product>> getAllProducts(
			@PathVariable("customerId") int customerId) {
		List<Product> products = productService.getAllProducts(customerId);
		
		if(products.isEmpty()){
			return new ResponseEntity<List<Product>>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<List<Product>>(products, HttpStatus.FOUND);
	}

	@RequestMapping(value = Constants.URI_CUSTOMER_PRODUCTS_BILL, method = RequestMethod.GET, produces = Constants.MEDIA_TYPE_JSON)
	public @ResponseBody ResponseEntity<CustomerBill> calculateAndReturnBill(
			@PathVariable("customerId") int customerId) {
		
		CustomerBill bill = productService.calculateAndReturnBill(customerId);
		if(null==bill){
			return new ResponseEntity<CustomerBill>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<CustomerBill>(bill, HttpStatus.OK);
	}

}
