/**
 * 
 */
package com.luv2code.springsecurity.demo.entities;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * @author Mihai-Tudor Popescu
 *
 */
@Data public class CrmUser {
	private static final String IS_REQUIRED_MESSAGE = "is required";

	@NotNull(message=IS_REQUIRED_MESSAGE)
	@Size(min=1, message=IS_REQUIRED_MESSAGE)
	private String userName;
	
	@NotNull(message=IS_REQUIRED_MESSAGE)
	@Size(min=1, message=IS_REQUIRED_MESSAGE)
	private String password;
}
