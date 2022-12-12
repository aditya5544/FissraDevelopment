package com.v2stech.fissara.exception;

import org.springframework.validation.BindingResult;

import org.springframework.validation.*;
public class InvalidFieldException extends  BindException {
	public InvalidFieldException(BindingResult result)
	{
		super(result);
	}

}

