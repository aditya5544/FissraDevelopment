package com.v2stech.fissara.serviceInterface;

import com.v2stech.fissara.exception.InvalidCredentialException;
import com.v2stech.fissara.model.UserCredentialsDTO;

public interface TemplateService {
	String getUserCredentials(UserCredentialsDTO credentials) throws InvalidCredentialException;

}
