package com.softwaretalks.jangul.models;

public enum EndpointProtocol implements IProtocol {
    HTTP {
        @Override
        public boolean isAddressValid(String address) {
            if (address == null || address.isEmpty())
                return false;
            return address.matches(HTTP_ADDRESS_PATTERN);
        }
    },
    TCP {
        @Override
        public boolean isAddressValid(String address) {
            if (address == null || address.isEmpty())
                return false;
            return address.matches(TCP_ADDRESS_PATTERN);
        }
    };


    private static final String HTTP_ADDRESS_PATTERN = "^(https?://)?(www\\.)?([\\w]+\\.)+[\\w]{2,63}/?$";
    private static final String TCP_ADDRESS_PATTERN = "^(tcp://)(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}):(\\d{1,5})$";
}
