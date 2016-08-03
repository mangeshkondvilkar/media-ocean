/**
 * 
 */
package org.mediaocean.rest.retail_checkout_counter.exceptions;

/**
 * @author mangesh
 *
 */
public class ResourceNotFoundExceptionMapper extends Exception{

	private static final long serialVersionUID = 1L;
	private String errorMessage;
 
	public ResourceNotFoundExceptionMapper() {
		super();
	}
	
	public ResourceNotFoundExceptionMapper(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
}
