package com.cqsd.data.serivce;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cqsd.data.entry.Employee;

import java.rmi.server.ExportException;

public interface EmployeeService extends IService<Employee> {
	String login(String username, String password) throws LoginError;
	
	 class LoginError extends Exception {
		 /**
		  * Constructs a new exception with the specified detail message.  The
		  * cause is not initialized, and may subsequently be initialized by
		  * a call to {@link #initCause}.
		  *
		  * @param message the detail message. The detail message is saved for
		  *                later retrieval by the {@link #getMessage()} method.
		  */
		 public LoginError(String message) {
			 super(message);
		 }
	 }
}

