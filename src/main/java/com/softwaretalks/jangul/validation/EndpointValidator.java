package com.softwaretalks.jangul.validation;

import com.softwaretalks.jangul.models.Endpoint;
import com.softwaretalks.jangul.models.EndpointProtocol;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EndpointValidator implements ConstraintValidator<ValidEndpoint, Endpoint> {

    private static final String HTTP_ADDRESS_PATTERN  = "^((https|http)?://)?(www\\.)?([\\w]+\\.)+[\\w]{2,63}/?$";
    private static final String TCP_ADDRESS_PATTERN  =  "^(tcp://)(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}):(\\d{1,5})$";


    @Override
    public void initialize(ValidEndpoint constraintAnnotation) {
    }

    @Override
    public boolean isValid(Endpoint endpoint, ConstraintValidatorContext constraintValidatorContext) {

        String address = endpoint.getAddress();
        EndpointProtocol protocol = endpoint.getProtocol();

        if (address == null || address.isEmpty() || protocol == null)
            return false;

        else switch (endpoint.getProtocol()) {

            case TCP : return endpoint.getAddress().matches(TCP_ADDRESS_PATTERN);

            case HTTP : return endpoint.getAddress().matches(HTTP_ADDRESS_PATTERN);

            default: return false;

        }




    }
}
