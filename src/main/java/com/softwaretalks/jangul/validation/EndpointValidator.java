package com.softwaretalks.jangul.validation;

import com.softwaretalks.jangul.models.Endpoint;
import com.softwaretalks.jangul.models.EndpointProtocol;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EndpointValidator implements ConstraintValidator<ValidEndpoint, Endpoint> {

    @Override
    public void initialize(ValidEndpoint constraintAnnotation) {
    }

    @Override
    public boolean isValid(Endpoint endpoint, ConstraintValidatorContext constraintValidatorContext) {

        String address = endpoint.getAddress();
        EndpointProtocol protocol = endpoint.getProtocol();

        return protocol.isAddressValid(address);

    }
}
